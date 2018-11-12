package br.com.workmade.cursomc.service;

import java.util.List;

import org.springframework.data.domain.Page;

import br.com.workmade.cursomc.domain.Produto;

public interface ProdutoService {

	public Produto buscarPorId(Integer id);
	
	public List<Produto> salvarProdutos(List<Produto> produtos);
	
	public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction);
	
}
