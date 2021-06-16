package com.ecommerce.services;

import com.ecommerce.domain.Categoria;
import com.ecommerce.exceptions.ObjectNotFoundException;
import com.ecommerce.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repo;

    public Categoria buscar(Integer id){
        Optional<Categoria> categoria = repo.findById(id);
        return categoria.orElseThrow(() -> new ObjectNotFoundException(
                "Object not found! Id: "+id +", type:" + Categoria.class.getName()));
    }
}
