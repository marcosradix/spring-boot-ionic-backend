package br.com.workmade.cursomc.service;

import java.util.List;

import br.com.workmade.cursomc.domain.Categoria;
import br.com.workmade.cursomc.service.exceptions.ObjectNotFoundException;



public interface CategoriaService {

	public Categoria buscarPorId(Integer id) throws ObjectNotFoundException;
	
	public List<Categoria> salvarTodos(List<Categoria> categorias);
	
	public Categoria salvarUm(Categoria categoria);
	public Categoria atualizar(Categoria categoria);
	public void deletar(Integer id);
	public List<Categoria> buscarTodos();
	
}
