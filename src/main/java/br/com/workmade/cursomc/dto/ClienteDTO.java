package br.com.workmade.cursomc.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import br.com.workmade.cursomc.domain.Cliente;

public class ClienteDTO implements Serializable {

	private static final long serialVersionUID = -4857312903332572783L;
	

	private Integer id;
	@NotEmpty(message="Campo de preenchimento obrigatório.")
	@Size(min=5, max=120, message="O tamanho deve ser entre {min} e {max}")
	private String nome;
	@Email(message="Infrome um e-mail válido.")
	@NotEmpty(message="Campo de preenchimento obrigatório.")
	private String email;
	

	public ClienteDTO(Cliente cliente) {
		this.id = cliente.getId();
		this.nome = cliente.getNome();
		this.email = cliente.getEmail();
	}
	public ClienteDTO() {}
	
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	
	
	
}
