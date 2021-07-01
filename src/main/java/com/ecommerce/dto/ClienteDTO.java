package com.ecommerce.dto;

import com.ecommerce.domain.Cliente;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@NoArgsConstructor
public class ClienteDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    private Integer id;
    @Getter
    @Setter
    @NotEmpty(message = "preenchimento obrigatório")
    @Length(min=5, max=120, message = "O tamanho deve ser de 5 a 120 caracteres")
    private String nome;
    @Getter
    @Setter
    @NotEmpty(message = "preenchimento obrigatório")
    @Email(message = "Email inválido")
    private String email;

    public ClienteDTO(Cliente obj){
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.email = obj.getEmail();
    }
}
