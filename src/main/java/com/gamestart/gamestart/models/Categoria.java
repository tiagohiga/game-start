package com.gamestart.gamestart.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_categoria")
public class Categoria {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCategoria;
	
	@NotNull
	private String nomeCategoria;
	
	private String descricaoCategoria;
	
	@OneToMany(mappedBy = "categoriaProduto", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonIgnoreProperties({"categoriaProduto", "descricaoProduto", "idProduto"})
	private List<Produto> produtosCadastrados;

	public Long getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Long idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getNomeCategoria() {
		return nomeCategoria;
	}

	public void setNomeCategoria(String nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
	}

	public String getDescricaoCategoria() {
		return descricaoCategoria;
	}

	public void setDescricaoCategoria(String descricaoCategoria) {
		this.descricaoCategoria = descricaoCategoria;
	}

	public List<Produto> getProdutosCadastrados() {
		return produtosCadastrados;
	}

	public void setProdutosCadastrados(List<Produto> produtosCadastrados) {
		this.produtosCadastrados = produtosCadastrados;
	}
}
