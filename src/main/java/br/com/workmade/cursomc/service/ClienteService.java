package br.com.workmade.cursomc.service;

import java.net.URI;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import br.com.workmade.cursomc.domain.Cliente;
import br.com.workmade.cursomc.dto.ClienteDTO;
import br.com.workmade.cursomc.dto.ClienteNovoDTO;
import br.com.workmade.cursomc.service.exceptions.ObjectNotFoundException;

public interface ClienteService {

	public Cliente salvarUm(Cliente cliente);
	
	public Cliente salvarComNovaSenha(Cliente cliente);
	
	public Cliente buscarPorEmail(String email);
	
	public List<Cliente> salvarTodos(List<Cliente> clientes);
	
	public Cliente buscarPorId(Integer id) throws ObjectNotFoundException;

	public Cliente atualizar(Cliente cliente);

	public Cliente fromDTO(ClienteDTO clienteDTO);
	
	public Cliente fromDTO(ClienteNovoDTO clienteNovoDTO);
	
	public void deletar(Integer id);

	public List<Cliente> buscarTodos();

	public Page<Cliente> buscarPorPagina(Integer page, Integer linesPerPage, String orderBy, String direction);
	
	public URI enviarFotoParaPerfil(MultipartFile multi);

	Cliente buscarPorEmailUsuarioLogado(String email);

	Cliente buscarPorEmailValidator(String email);
	
}
