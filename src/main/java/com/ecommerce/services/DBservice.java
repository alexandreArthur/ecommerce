package com.ecommerce.services;

import com.ecommerce.Enums.EstadoPagamento;
import com.ecommerce.Enums.Perfil;
import com.ecommerce.Enums.TipoCliente;
import com.ecommerce.domain.*;
import com.ecommerce.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;

@Service
public class DBservice {

    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private EstadoRepository estadoRepository;
    @Autowired
    private CidadeRepository cidadeRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private PagamentoRepository pagamentoRepository;
    @Autowired
    private ItemPedidoRepository itemPedidoRepository;
    @Autowired
    private BCryptPasswordEncoder pe;

    public void instantiateTestDataBase() throws ParseException {

        Categoria cat1 = new Categoria(null,"Informática" );
        Categoria cat2 = new Categoria(null,"Escritório" );
        Categoria cat3 = new Categoria(null,"Cama mesa e banho" );
        Categoria cat4 = new Categoria(null,"Eletrônicos" );
        Categoria cat5 = new Categoria(null,"Jardinagem" );
        Categoria cat6 = new Categoria(null,"Decoração" );
        Categoria cat7 = new Categoria(null,"Perfumaria" );


        Produto p1 = new Produto(null, "Computador", 2000.0);
        Produto p2 = new Produto(null, "Impressora", 800.00);
        Produto p3 = new Produto(null, "Mouse", 80.00);
        Produto p4 = new Produto(null, "Mesa de escritório", 380.00);
        Produto p5 = new Produto(null, "Toalha", 80.00);
        Produto p6 = new Produto(null, "colcha", 280.00);
        Produto p7 = new Produto(null, "Tv true color", 1800.00);
        Produto p8 = new Produto(null, "Roçadeira", 800.00);
        Produto p9 = new Produto(null, "Abajour", 500.00);
        Produto p10 = new Produto(null, "Pendente", 180.00);
        Produto p11 = new Produto(null, "Shampoo", 80.00);


        cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
        cat2.getProdutos().addAll(Arrays.asList(p2,p4));
        cat3.getProdutos().addAll(Arrays.asList(p5,p6));
        cat4.getProdutos().addAll(Arrays.asList(p1,p2,p3,p7));
        cat5.getProdutos().addAll(Collections.singletonList(p8));

        cat6.getProdutos().addAll(Arrays.asList(p9,p10));
        cat7.getProdutos().addAll(Collections.singletonList(p11));


        p1.getCategorias().addAll(Arrays.asList(cat1,cat4));
        p2.getCategorias().addAll(Arrays.asList(cat1,cat2,cat4));
        p3.getCategorias().addAll(Arrays.asList(cat1,cat4));
        p4.getCategorias().addAll(Collections.singletonList(cat2));
        p5.getCategorias().addAll(Collections.singletonList(cat3));
        p6.getCategorias().addAll(Collections.singletonList(cat3));
        p7.getCategorias().addAll(Collections.singletonList(cat4));
        p8.getCategorias().addAll(Collections.singletonList(cat5));
        p9.getCategorias().addAll(Collections.singletonList(cat6));
        p10.getCategorias().addAll(Collections.singletonList(cat6));
        p11.getCategorias().addAll(Collections.singletonList(cat7));



        categoriaRepository.saveAll(Arrays.asList(cat1,cat2,cat3,cat4,cat5,cat6,cat7));
        produtoRepository.saveAll(Arrays.asList(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11));

        Estado est1 = new Estado(null, "Minas Gerais");
        Estado est2 = new Estado(null, "São Paulo");

        Cidade c1 = new Cidade(null,"Uberlândia", est1);
        Cidade c2 = new Cidade(null,"São Paulo", est2);
        Cidade c3 = new Cidade(null,"Campinas", est2);

        est1.getCidades().addAll(Collections.singletonList(c1));
        est1.getCidades().addAll(Arrays.asList(c2,c3));

        estadoRepository.saveAll(Arrays.asList(est1,est2));
        cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));

        Cliente cli1 = new Cliente(null, "Maria Silva","doncontafake@gmail.com","36378912377", TipoCliente.PESSOA_FISICA
        ,pe.encode("777"));

        Cliente cli2 = new Cliente(null, "Ana costa","doncontafake4@hotmail.com","22383141054", TipoCliente.PESSOA_FISICA
                ,pe.encode("999"));
        cli2.addPerfil(Perfil.ADMIN);

        cli1.getTelefones().addAll(Arrays.asList("27363323","9383893"));
        cli2.getTelefones().addAll(Arrays.asList("31639323","1384793"));

        Endereco e1 = new Endereco(null,"Rua flores",
                "300", "Apto 303", "Jardim", "38220834", cli1,c1);
        Endereco e2 = new Endereco(null,"Avenida Matos",
                "105", "Sala  800", "Centro", "38777012", cli1,c2);

        Endereco e3 = new Endereco(null,"Av Floriano",
                "2106", null, "Centro", "112244551", cli2,c2);

        cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
        cli2.getEnderecos().addAll(Collections.singletonList(e3));

        clienteRepository.saveAll(Arrays.asList(cli1,cli2));
        enderecoRepository.saveAll(Arrays.asList(e1,e2,e3));

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        Pedido ped1 = new Pedido(null, sdf.parse("30/09/2020 10:32"), cli1, e1);
        Pedido ped2 = new Pedido(null, sdf.parse("10/10/2021 10:32"), cli1, e2);

        Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO,ped1,6);
        ped1.setPagamento(pagto1);

        Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE,ped2,sdf.parse("20/10/2017 00:00"), null);
        ped2.setPagamento(pagto2);

        cli1.getPedidos().addAll(Arrays.asList(ped1,ped2));

        pedidoRepository.saveAll(Arrays.asList(ped1,ped2));
        pagamentoRepository.saveAll(Arrays.asList(pagto1,pagto2));

        ItemPedido ip1 = new ItemPedido(ped1,p1,0.00,1,2000.00);
        ItemPedido ip2 = new ItemPedido(ped1,p3,0.00,2,80.00);
        ItemPedido ip3 = new ItemPedido(ped2,p2,100.00,1,800.00);

        ped1.getItens().addAll(Arrays.asList(ip1,ip2));
        ped2.getItens().addAll(Collections.singletonList(ip3));

        p1.getItens().addAll(Collections.singletonList(ip1));
        p2.getItens().addAll(Collections.singletonList(ip3));
        p3.getItens().addAll(Collections.singletonList(ip2));

        itemPedidoRepository.saveAll(Arrays.asList(ip1,ip2,ip3));
    }
}