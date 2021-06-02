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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gamestart.gamestart.models.Categoria;
import com.gamestart.gamestart.repositories.CategoriaRepository;
import com.gamestart.gamestart.services.CategoriaService;

@RestController
@RequestMapping("/categoria")
@CrossOrigin("*")
public class CategoriaController {
	@Autowired
	private CategoriaRepository repository;
	@Autowired
	private CategoriaService service;
	
	@GetMapping("/todes")
	public ResponseEntity<List<Categoria>> findAllCategoria(){
		List<Categoria> listaCategorias = repository.findAll();
		if(listaCategorias.isEmpty()) {
			return ResponseEntity.status(204).build();
		}else {
			return ResponseEntity.status(200).body(listaCategorias);
		}
	}
	
	@GetMapping("/id/{id_categoria}")
	public ResponseEntity<Categoria> findByIdCategoria(@PathVariable(value = "id_categoria") Long idCategoria){
		return repository.findById(idCategoria)
				.map(categoriaExistente -> ResponseEntity.status(200).body(categoriaExistente))
				.orElse(ResponseEntity.status(204).build());
	}
	
	@GetMapping("/descricao")
	public ResponseEntity<Object> findByDescricao(@RequestParam(defaultValue = "") String descricaoCategoria){
		List<Categoria> listaDescricoesCategorias = repository.findAllByDescricaoCategoriaContainingIgnoreCase(descricaoCategoria);
		if(listaDescricoesCategorias.isEmpty()) {
			return ResponseEntity.status(204).build();
		}else {
			return ResponseEntity.status(200).body(listaDescricoesCategorias);
		}
	}
	
	@PostMapping("/cadastrar")
	public ResponseEntity<Categoria> postCategoria(@Valid @RequestBody Categoria categoria){
		return service.cadastrarCategoria(categoria)
				.map(categoriaCriada -> ResponseEntity.status(200).body(categoriaCriada))
				.orElse(ResponseEntity.status(204).build());
	}
	
	@PutMapping("/alterar/{id_categoria}")
	public ResponseEntity<Categoria> putCategoria(@PathVariable(value = "id_categoria") Long idCategoria, 
			@RequestBody Categoria atualizacaoCategoria){
		return service.alterarCategoria(idCategoria, atualizacaoCategoria)
				.map(categoriaAtualizada -> ResponseEntity.status(201).body(categoriaAtualizada))
				.orElse(ResponseEntity.status(304).build());
	}
	
	@DeleteMapping("/deletar/{id_categoria}")
	public void deleteCategoria(@PathVariable(value = "id_categoria") long idCategoria) {
		repository.deleteById(idCategoria);
	}
}
