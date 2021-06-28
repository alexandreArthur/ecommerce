package com.ecommerce.services;

import com.ecommerce.domain.Categoria;
import com.ecommerce.domain.Cliente;
import com.ecommerce.exceptions.ObjectNotFoundException;
import com.ecommerce.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository repo;

    public Cliente find(Integer id){
        Optional<Cliente> cliente = repo.findById(id);
        return cliente.orElseThrow(() -> new ObjectNotFoundException(
                "Object not found! Id: "+id +", type:" + Cliente.class.getName()));
    }
}
