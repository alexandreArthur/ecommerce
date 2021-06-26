package com.ecommerce.services;

import com.ecommerce.domain.Pedido;
import com.ecommerce.exceptions.ObjectNotFoundException;
import com.ecommerce.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repo;

    public Pedido buscar(Integer id){
        Optional<Pedido> Pedido = repo.findById(id);
        return Pedido.orElseThrow(() -> new ObjectNotFoundException(
                "Object not found! Id: "+id +", type:" + Pedido.class.getName()));
    }
}
