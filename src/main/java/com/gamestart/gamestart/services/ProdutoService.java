package com.gamestart.gamestart.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gamestart.gamestart.models.Produto;
import com.gamestart.gamestart.repositories.ProdutoRepository;

@Service
public class ProdutoService {
	@Autowired
	private ProdutoRepository repositoryP;
	
	public Optional<Produto> cadastrarProduto(Produto novoProduto){
		Optional<Produto> produtoExistente = repositoryP.findByTituloProduto(novoProduto.getTituloProduto());
		
		if(produtoExistente.isPresent()) {
			return Optional.empty();
		}else {
			return Optional.ofNullable(repositoryP.save(novoProduto));
		}
	}
	
	public Optional<Produto> alterarCategoria(Long idCategoria, Produto atualizacaoProduto){
		Optional<Produto> produtoExistente = repositoryP.findById(idCategoria);
		if (produtoExistente.isPresent()) {
			produtoExistente.get().setDescricaoProduto(atualizacaoProduto.getDescricaoProduto());
			produtoExistente.get().setPrecoProduto(atualizacaoProduto.getPrecoProduto());
			return Optional.ofNullable(repositoryP.save(produtoExistente.get()));
		} else {
			return Optional.empty();
		}
	}
}
