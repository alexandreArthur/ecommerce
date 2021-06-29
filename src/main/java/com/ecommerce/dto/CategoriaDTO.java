package com.ecommerce.dto;

import com.ecommerce.domain.Categoria;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
public class CategoriaDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    private Integer id;
    @Getter
    @Setter
    private String nome;

    public CategoriaDTO(Categoria obj){
        this.id = obj.getId();
        this.nome = obj.getNome();
    }
}
