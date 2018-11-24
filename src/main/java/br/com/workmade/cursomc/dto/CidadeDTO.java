package br.com.workmade.cursomc.dto;

import java.io.Serializable;

import br.com.workmade.cursomc.domain.Cidade;

public class CidadeDTO implements Serializable{

	private static final long serialVersionUID = -1222073571110079652L;
	private Integer id;
	private String nome;
	
	
	
	public CidadeDTO() {}

	public CidadeDTO(Cidade cidade) {
		this.id = cidade.getId();
		this.nome = cidade.getNome();
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
