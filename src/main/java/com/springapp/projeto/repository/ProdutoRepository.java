package com.springapp.projeto.repository;

import org.springframework.data.repository.CrudRepository;

import com.springapp.projeto.model.Produto;

public interface ProdutoRepository extends CrudRepository<Produto, String> {
	Produto findByCodigoProduto(long codigoProduto);
}
