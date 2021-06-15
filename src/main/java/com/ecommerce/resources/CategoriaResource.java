package com.ecommerce.resources;

import com.ecommerce.domain.Categoria;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {

    @RequestMapping(method = RequestMethod.GET)
    public List<Categoria> listar(){

        Categoria categ1 = Categoria.builder().id(1).nome("Informática").build();

        Categoria categ2 = Categoria.builder().id(2).nome("Escritório").build();

        List<Categoria> list = new ArrayList<>();
        list.add(categ1);
        list.add(categ2);

        return list;
    }
}
