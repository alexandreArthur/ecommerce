package com.ecommerce.services;

import com.ecommerce.domain.Categoria;
import com.ecommerce.dto.CategoriaDTO;
import com.ecommerce.exceptions.DataIntegrityException;
import com.ecommerce.exceptions.ObjectNotFoundException;
import com.ecommerce.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repo;

    public Categoria find(Integer id){
        Optional<Categoria> categoria = repo.findById(id);
        return categoria.orElseThrow(() -> new ObjectNotFoundException(
                "Object not found! Id: "+id +", type:" + Categoria.class.getName()));
    }

    public Categoria insert(Categoria obj) {
        obj.setId(null);
        return repo.save(obj);

    }

    public Categoria put(Categoria obj) {
        Categoria newObj = find(obj.getId());
        updateData(newObj, obj);
        return repo.save(newObj);
    }
    private void updateData(Categoria newObj, Categoria obj){
        newObj.setNome(obj.getNome());

    }

    public void delete(Integer id) {
        find(id);
        try{
            repo.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("Não é possível excluir uma categoria com produtos!");
        }

    }

    public List<Categoria> findAll() {
        return repo.findAll();
    }

    public Page<Categoria> findByPages(Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction),
                orderBy);
        return repo.findAll(pageRequest);
    }

    public Categoria fromDTO(CategoriaDTO objDto){
        return new Categoria(objDto.getId(), objDto.getNome());
    }
}
