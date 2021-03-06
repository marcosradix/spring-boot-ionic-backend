package br.com.workmade.cursomc.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import br.com.workmade.cursomc.domain.Categoria;

public class CategoriaDTO implements Serializable{

	private static final long serialVersionUID = -1877678918117790384L;

	private Integer id;
	
	@NotEmpty(message="Campo nome é obrigatório.")
	@Size(min=5, max=80, message="O tamanho de ser no mínimo {min} e no máximo {max}")
	private String nome;
	
	

	public CategoriaDTO() {}

	public CategoriaDTO(Categoria categoria) {
		this.id = categoria.getId();
		this.nome = categoria.getNome();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	

}
