package com.gamestart.gamestart.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gamestart.gamestart.models.Produto;
import com.gamestart.gamestart.repositories.ProdutoRepository;
import com.gamestart.gamestart.services.ProdutoService;

@RestController
@RequestMapping("/produto")
@CrossOrigin("*")
public class ProdutoController {
	@Autowired
	private ProdutoRepository repository;
	
	@Autowired
	private ProdutoService service;
	
	@GetMapping("/todes")
	public ResponseEntity<List<Produto>> findAllProduto(){
		List<Produto> listaProdutos = repository.findAll();
		if(listaProdutos.isEmpty()) {
			return ResponseEntity.status(204).build();
		}else {
			return ResponseEntity.status(201).body(listaProdutos);
		}
	}
	
	@GetMapping("/produtos/id/{id_produto}")
	public ResponseEntity<Produto> findByIdProduto(@PathVariable(value = "id_produto") Long idProduto){
		return repository.findById(idProduto)
				.map(produtoExistente -> ResponseEntity.status(200).body(produtoExistente))
				.orElse(ResponseEntity.status(204).build());
	}
	
	@GetMapping("/produtos/{titulo_produto}")
	public ResponseEntity<Produto> findByDescricaoTitulo(@PathVariable(value = "titulo_produto") String tituloProduto){
		return repository.findByTituloProduto(tituloProduto)
				.map(produtoExistente -> ResponseEntity.status(200).body(produtoExistente))
				.orElse(ResponseEntity.status(204).build());
	}
	
	@PostMapping("/cadastrar")
	public ResponseEntity<Produto> postProduto(@Valid @RequestBody Produto produto){
		return service.cadastrarProduto(produto)
				.map(produtoCadastrado -> ResponseEntity.status(201).body(produtoCadastrado))
				.orElse(ResponseEntity.status(204).build());
	}
	
	@PutMapping("/alterar/{id_produto}")
	public ResponseEntity<Produto> putProduto(@PathVariable(value = "id_produto") Long idProduto, 
			@RequestBody Produto atualizacaoProduto){
		return service.alterarCategoria(idProduto, atualizacaoProduto)
				.map(produtoAlterado -> ResponseEntity.status(201).body(produtoAlterado))
				.orElse(ResponseEntity.status(304).build());
	}
	
	@DeleteMapping("/deletar/{id_produto}")
	public void deleteProduto(@PathVariable(value = "id_produto") Long idProduto) {
		repository.deleteById(idProduto);
	}
}
