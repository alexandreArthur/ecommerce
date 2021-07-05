package com.ecommerce.dto;

import com.ecommerce.domain.Produto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
public class ProdutoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    private Integer id;
    @Getter
    @Setter
    private String nome;
    @Getter
    @Setter
    private Double preco;

    public ProdutoDTO(Produto obj){
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.preco = obj.getPreco();
    }
}
