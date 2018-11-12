package br.com.workmade.cursomc.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import br.com.workmade.cursomc.domain.Produto;

public class ProdutoDTO implements Serializable {

	private static final long serialVersionUID = -574237950513635852L;
	private Integer id;
	private String nome;
	private BigDecimal preco;
	
	
	public ProdutoDTO() {}
	
	public ProdutoDTO(Produto produto) {
		this.id = produto.getId();
		this.preco = produto.getPreco();
		this.nome = produto.getNome();
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
	public BigDecimal getPreco() {
		return preco;
	}
	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	
}
