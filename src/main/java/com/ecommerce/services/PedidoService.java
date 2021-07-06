package com.ecommerce.services;

import com.ecommerce.Enums.EstadoPagamento;
import com.ecommerce.domain.ItemPedido;
import com.ecommerce.domain.PagamentoComBoleto;
import com.ecommerce.domain.Pedido;
import com.ecommerce.exceptions.ObjectNotFoundException;
import com.ecommerce.repositories.ItemPedidoRepository;
import com.ecommerce.repositories.PagamentoRepository;
import com.ecommerce.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Collections;
import java.util.Date;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repo;
    @Autowired
    private BoletoService boletoService;
    @Autowired
    private PagamentoRepository pagamentoRepository;
    @Autowired
    private ProdutoService produtoService;
    @Autowired
    ItemPedidoRepository itemPedidoRepository;

    public Pedido find(Integer id){
        Optional<Pedido> Pedido = repo.findById(id);
        return Pedido.orElseThrow(() -> new ObjectNotFoundException(
                "Object not found! Id: "+id +", type:" + Pedido.class.getName()));
    }

    @Transactional
    public Pedido insert(Pedido obj) {
        obj.setId(null);
        obj.setInstante(new Date());
        obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
        obj.getPagamento().setPedido(obj);
        if(obj.getPagamento() instanceof PagamentoComBoleto){
            PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
            boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante());
        }
        obj = repo.save(obj);
        pagamentoRepository.saveAll(Collections.singletonList(obj.getPagamento()));
        for(ItemPedido ip: obj.getItens()){
            ip.setDesconto(0.0);
            ip.setPreco(produtoService.find(ip.getProduto().getId()).getPreco());
            ip.setPedido(obj);
        }
        itemPedidoRepository.saveAll(obj.getItens());
        return obj;

    }
}
