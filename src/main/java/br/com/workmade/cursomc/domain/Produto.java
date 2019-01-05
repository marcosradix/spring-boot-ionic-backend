package br.com.workmade.cursomc.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Produto implements Serializable {

	private static final long serialVersionUID = -5766114679804701593L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private BigDecimal preco;
	private StringBuilder descricao;
	
	
	// 'produto_id' chave dessa tabela, chave 'categoria_id' da outra tabela
	@JsonIgnore
	@ManyToMany
	@JoinTable(name="PRODUTO_CATEGORIA" , joinColumns = @JoinColumn(name= "produto_id"),
	inverseJoinColumns = @JoinColumn(name= "categoria_id"))
	private List<Categoria> categorias = new ArrayList<>();

	@JsonIgnore
	@OneToMany(mappedBy="id.produto")
	private Set<ItemPedido> itens = new HashSet<>();
	
	public Produto() {}
	
	@JsonIgnore
	public List<Pedido> getPedidos(){
		List<Pedido> lista = new ArrayList<>();
		for (ItemPedido itemPedido : itens) {
			lista.add(itemPedido.getPedido());
		}
		
		return lista;
	}

	public Produto(Integer id, String nome, BigDecimal preco) {
		super();
		this.id = id;
		this.nome = nome;
		this.preco = preco;
	}
	
	public Produto(Integer id, String nome, BigDecimal preco, StringBuilder descricao) {
		super();
		this.id = id;
		this.nome = nome;
		this.preco = preco;
		this.descricao = descricao;
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

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}
	public Set<ItemPedido> getItens() {
		return itens;
	}

	public void setItens(Set<ItemPedido> itens) {
		this.itens = itens;
	}
	
	public StringBuilder getDescricao() {
		return descricao;
	}

	public void setDescricao(StringBuilder descricao) {
		this.descricao = descricao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Produto [id=");
		builder.append(id);
		builder.append(", nome=");
		builder.append(nome);
		builder.append(", preco=");
		builder.append(preco);
		builder.append(", descricao=");
		builder.append(descricao);
		builder.append(", categorias=");
		builder.append(categorias);
		builder.append(", itens=");
		builder.append(itens);
		builder.append("]");
		return builder.toString();
	}

	

	
	
}
