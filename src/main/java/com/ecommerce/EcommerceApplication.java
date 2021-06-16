package com.ecommerce;

import com.ecommerce.domain.Categoria;
import com.ecommerce.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class EcommerceApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;

	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Categoria cat1 = Categoria.builder().id(null).nome("Informática").build();
		Categoria cat2 = Categoria.builder().id(null).nome("Escritório").build();
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));


	}
}
