package br.com.workmade.cursomc.service;

import java.util.List;

import br.com.workmade.cursomc.domain.Estado;

public interface EstadoService {

	public Estado salvar(Estado estado);
	
	public List<Estado> salvarTodos(List<Estado> estados);
	
	public List<Estado> buscarEstados();
}
