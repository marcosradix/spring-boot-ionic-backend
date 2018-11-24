package br.com.workmade.cursomc.serviceImpl;

import java.awt.image.BufferedImage;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import br.com.workmade.cursomc.domain.Cidade;
import br.com.workmade.cursomc.domain.Cliente;
import br.com.workmade.cursomc.domain.Endereco;
import br.com.workmade.cursomc.domain.enums.Perfil;
import br.com.workmade.cursomc.domain.enums.TipoCliente;
import br.com.workmade.cursomc.dto.ClienteDTO;
import br.com.workmade.cursomc.dto.ClienteNovoDTO;
import br.com.workmade.cursomc.repositories.ClienteRepository;
import br.com.workmade.cursomc.repositories.EnderecoRepository;
import br.com.workmade.cursomc.security.UserSS;
import br.com.workmade.cursomc.service.ClienteService;
import br.com.workmade.cursomc.service.ImageService;
import br.com.workmade.cursomc.service.S3Service;
import br.com.workmade.cursomc.service.UserService;
import br.com.workmade.cursomc.service.exceptions.AuthorizationException;
import br.com.workmade.cursomc.service.exceptions.DataIntegrityException;
import br.com.workmade.cursomc.service.exceptions.ObjectNotFoundException;

@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private S3Service s3Service;
	
	@Autowired
	private ImageService imageService;
	
	@Value("${img.prefix.client.profile}")
	private String prefix;
	
	@Value("${img.profile.size}")
	private Integer sizeImg;
	
	@Override
	@Transactional
	public Cliente salvarUm(Cliente cliente) {
		cliente.setId(null);
		cliente = clienteRepository.save(cliente);
		enderecoRepository.saveAll(cliente.getEnderecos());
		return cliente;
	}
	
	public Cliente salvarComNovaSenha(Cliente cliente) {
		cliente = clienteRepository.save(cliente);
		enderecoRepository.saveAll(cliente.getEnderecos());
		return cliente;
	}

	@Override
	public Cliente buscarPorId(Integer id) throws ObjectNotFoundException {
		UserSS user = UserService.authenticated();
		if(  (user == null || !user.hasRole(Perfil.ADMIN)) && !id.equals(user.getId())  ) {
			throw new AuthorizationException("Acesso negado.");
		}
		
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
		return new Cliente(categoriaDTO.getId(), categoriaDTO.getNome(),categoriaDTO.getEmail(), null, null, null);
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
		Cliente cliente = new Cliente(null, clienteNovoDTO.getNome(), clienteNovoDTO.getEmail(), clienteNovoDTO.getCpfOuCnpj(), TipoCliente.paraEnum(clienteNovoDTO.getTipo()),
				bCryptPasswordEncoder.encode(clienteNovoDTO.getSenha()));
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
		Optional<Cliente> cliente = clienteRepository.findByEmail(email); 
		return cliente.orElseThrow(() -> new ObjectNotFoundException(
				"O email não foi encontrado, email : "+email));
	}
	
	@Override
	public Cliente buscarPorEmailUsuarioLogado(String email) {
		UserSS user = UserService.authenticated();	
		if(user == null || (!email.equals( user.getUsername() ) || !user.hasRole(Perfil.ADMIN)) ){
			throw new AuthorizationException("Acesso negado.");
		}
			Optional<Cliente> cliente = clienteRepository.findByEmail(email); 
			return cliente.orElseThrow(() -> new ObjectNotFoundException(
					"O email não foi encontrado, email : "+email));
	}

	@Override
	public URI enviarFotoParaPerfil(MultipartFile multi) {
		UserSS user = UserService.authenticated();
		if (user == null) {
			throw new AuthorizationException("Acesso negado.");
		}
		BufferedImage jpgImage = imageService.getJpgImageFromFile(multi);
		jpgImage = imageService.cropSquare(jpgImage);
		jpgImage= imageService.resize(jpgImage, sizeImg);
		
		
		String fileName = prefix + user.getId() + ".jpg";
		return  s3Service.uploadFile(imageService.getInputStream(jpgImage, "jpg"), fileName, "image");

	}

	
}
