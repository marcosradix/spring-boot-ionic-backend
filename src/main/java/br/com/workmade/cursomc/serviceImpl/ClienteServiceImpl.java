package br.com.workmade.cursomc.serviceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.workmade.cursomc.domain.Cidade;
import br.com.workmade.cursomc.domain.Cliente;
import br.com.workmade.cursomc.domain.Endereco;
import br.com.workmade.cursomc.domain.enums.TipoCliente;
import br.com.workmade.cursomc.dto.ClienteDTO;
import br.com.workmade.cursomc.dto.ClienteNovoDTO;
import br.com.workmade.cursomc.repositories.ClienteRepository;
import br.com.workmade.cursomc.repositories.EnderecoRepository;
import br.com.workmade.cursomc.service.ClienteService;
import br.com.workmade.cursomc.service.exceptions.DataIntegrityException;
import br.com.workmade.cursomc.service.exceptions.ObjectNotFoundException;
@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Override
	@Transactional
	public Cliente salvarUm(Cliente cliente) {
		cliente.setId(null);
		cliente = clienteRepository.save(cliente);
		enderecoRepository.saveAll(cliente.getEnderecos());
		return cliente;
	}

	@Override
	public Cliente buscarPorId(Integer id) throws ObjectNotFoundException {
		Optional<Cliente> cliente = clienteRepository.findById(id); 
		return cliente.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id : "+id+" : "+ Cliente.class.getName()));
	}

	@Override
	@Transactional
	public List<Cliente> salvarTodos(List<Cliente> clientes) {
		return clienteRepository.saveAll(clientes);
	}

	@Override
	@Transactional
	public Cliente atualizar(Cliente cliente) {
		Cliente newCliente  = buscarPorId(cliente.getId());
			atualizarAntesDesalvar(newCliente,cliente );
		return clienteRepository.save(newCliente);

		
	}

	private void atualizarAntesDesalvar(Cliente newCliente, Cliente cliente) {
		newCliente.setNome(cliente.getNome());
		newCliente.setEmail(cliente.getEmail());
		
	}

	@Override
	public Cliente fromDTO(ClienteDTO categoriaDTO) {
		return new Cliente(categoriaDTO.getId(), categoriaDTO.getNome(),categoriaDTO.getEmail(), null, null);
	}

	@Override
	public void deletar(Integer id) {
		buscarPorId(id);
		try {
			clienteRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir um cliente que tem pedidos!");
		}
		
	}

	@Override
	public List<Cliente> buscarTodos() {
		return clienteRepository.findAll();
	}

	@Override
	public Page<Cliente> buscarPorPagina(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return clienteRepository.findAll(pageRequest);
	}

	@Override
	public Cliente fromDTO(ClienteNovoDTO clienteNovoDTO) {
		Cliente cliente = new Cliente(null, clienteNovoDTO.getNome(), clienteNovoDTO.getEmail(), clienteNovoDTO.getCpfOuCnpj(), TipoCliente.paraEnum(clienteNovoDTO.getTipo()));
		Cidade cidade = new Cidade(clienteNovoDTO.getCidadeId(), null, null);
		Endereco endereco = new Endereco(null, clienteNovoDTO.getLogradouro(),
				clienteNovoDTO.getNumero(), clienteNovoDTO.getComplemento(),
				clienteNovoDTO.getBairro(), clienteNovoDTO.getCep(), cliente, cidade);
		cliente.getEnderecos().add(endereco);
		cliente.getTelefones().addAll(Arrays.asList(clienteNovoDTO.getTelefone1(), clienteNovoDTO.getTelefone2(), clienteNovoDTO.getTelefone3()));
		return cliente;
	}

	@Override
	public Cliente buscarPorEmail(String email) {
		return clienteRepository.findByEmail(email);
	}



}
