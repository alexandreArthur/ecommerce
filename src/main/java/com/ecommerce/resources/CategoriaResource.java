package com.ecommerce.resources;

import com.ecommerce.domain.Categoria;
import com.ecommerce.dto.CategoriaDTO;
import com.ecommerce.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {

    @Autowired
    private CategoriaService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Categoria> find(@PathVariable Integer id){

        Categoria obj = service.find(id);
        return ResponseEntity.ok().body(obj);

    }
    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> findAll(){

        List<Categoria> list = service.findAll();
        List<CategoriaDTO> listDto = list.stream().map(CategoriaDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);

    }

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody Categoria obj){
        obj = service.insert(obj);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }
    @PutMapping(value="/{id}")
    public ResponseEntity<Void> update(@PathVariable Integer id, @RequestBody Categoria obj){
        obj.setId(id);
        service.put(obj);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

