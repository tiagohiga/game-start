package com.gamestart.gamestart.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gamestart.gamestart.models.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{
	Optional<Produto> findByTituloProduto(String tituloProduto);
}
