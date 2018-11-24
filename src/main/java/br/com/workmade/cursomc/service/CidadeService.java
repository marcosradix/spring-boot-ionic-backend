package br.com.workmade.cursomc.service;

import java.util.List;

import br.com.workmade.cursomc.domain.Cidade;

public interface CidadeService {
	public Cidade salvar(Cidade cidade);
	
	public List<Cidade> salvarTodos(List<Cidade> cidades);

	public List<Cidade> listarCidadesPorIdEstado(Integer estadoId);
	
}
