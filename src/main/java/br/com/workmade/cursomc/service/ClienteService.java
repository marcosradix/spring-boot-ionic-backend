package br.com.workmade.cursomc.service;

import java.util.List;

import org.springframework.data.domain.Page;

import br.com.workmade.cursomc.domain.Cliente;
import br.com.workmade.cursomc.dto.ClienteDTO;
import br.com.workmade.cursomc.service.exceptions.ObjectNotFoundException;

public interface ClienteService {

	public Cliente salvarUm(Cliente cliente);
	
	public List<Cliente> salvarTodos(List<Cliente> clientes);
	
	public Cliente buscarPorId(Integer id) throws ObjectNotFoundException;

	public Cliente atualizar(Cliente categoria);

	public Cliente fromDTO(ClienteDTO categoriaDTO);

	public void deletar(Integer id);

	public List<Cliente> buscarTodos();

	public Page<Cliente> buscarPorPagina(Integer page, Integer linesPerPage, String orderBy, String direction);
}
