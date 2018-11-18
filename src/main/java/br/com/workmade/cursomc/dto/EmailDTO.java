package br.com.workmade.cursomc.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class EmailDTO implements Serializable{
	
	private static final long serialVersionUID = 1820811166823389755L;
	@Email(message="Infrome um e-mail válido.")
	@NotEmpty(message="Campo email é de preenchimento obrigatório.")
	private String email;
	
	public EmailDTO() {}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


}
