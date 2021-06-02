package com.gamestart.gamestart.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gamestart.gamestart.models.Categoria;
import com.gamestart.gamestart.repositories.CategoriaRepository;

@Service
public class CategoriaService {
	@Autowired
	private CategoriaRepository repositoryC;
	
	public Optional<Categoria> cadastrarCategoria(Categoria categoria){
		Optional<Categoria> categoriaExistente = repositoryC.findByNomeCategoria(categoria.getNomeCategoria());
		if(categoriaExistente.isPresent()) {
			return Optional.empty();
		}else {
			return Optional.ofNullable(repositoryC.save(categoria));
		}
	}
	
	public Optional<Categoria> alterarCategoria(Long idCategoria, Categoria atualizacaoCategoria){
		Optional<Categoria> categoriaExistente = repositoryC.findById(idCategoria);
		if (categoriaExistente.isPresent()) {
			categoriaExistente.get().setDescricaoCategoria(atualizacaoCategoria.getDescricaoCategoria());
			return Optional.ofNullable(repositoryC.save(categoriaExistente.get()));
		} else {
			return Optional.empty();
		}
	}
}
