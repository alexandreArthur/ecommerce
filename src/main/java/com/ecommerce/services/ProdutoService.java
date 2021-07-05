package com.ecommerce.services;

import com.ecommerce.domain.Categoria;
import com.ecommerce.domain.Produto;
import com.ecommerce.exceptions.ObjectNotFoundException;
import com.ecommerce.repositories.CategoriaRepository;
import com.ecommerce.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repo;
    @Autowired
    private CategoriaRepository categoriaRepository;

    public Produto find(Integer id){
        Optional<Produto> produto = repo.findById(id);
        return produto.orElseThrow(() -> new ObjectNotFoundException(
                "Object not found! Id: "+id +", type:" + Produto.class.getName()));
    }
    public Page<Produto> search(String nome, List<Integer> ids,Integer page, Integer linesPerPage, String orderBy, String direction){

        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction),
                orderBy);

        List<Categoria> categorias = categoriaRepository.findAllById(ids);

        return repo.findDistinctByNomeContainingAndCategoriasIn(nome,categorias, pageRequest);

    }
}
