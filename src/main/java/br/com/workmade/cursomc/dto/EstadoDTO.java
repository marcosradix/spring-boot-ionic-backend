package br.com.workmade.cursomc.dto;

import java.io.Serializable;

import br.com.workmade.cursomc.domain.Estado;

public class EstadoDTO implements Serializable{

	private static final long serialVersionUID = -1948056178343792871L;
	
	private Integer id;
	private String nome;
	
	public EstadoDTO() {}
	
	

	public EstadoDTO(Estado estado) {
		this.id = estado.getId();
		this.nome = estado.getNome();
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
