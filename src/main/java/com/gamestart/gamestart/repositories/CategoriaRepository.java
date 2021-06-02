package com.gamestart.gamestart.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gamestart.gamestart.models.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long>{
	public List<Categoria> findAllByDescricaoCategoriaContainingIgnoreCase(String categoria);
	
	public Optional<Categoria> findByNomeCategoria(String categoria);
}
