package br.com.workmade.cursomc.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.workmade.cursomc.domain.Cliente;
import br.com.workmade.cursomc.dto.ClienteDTO;
import br.com.workmade.cursomc.dto.ClienteNovoDTO;
import br.com.workmade.cursomc.service.ClienteService;

@RestController
@RequestMapping(value={"/"})
@CrossOrigin(origins = "*")
public class ClienteResources {
	
	@Autowired
	private ClienteService clienteService;

	
	@RequestMapping(method=RequestMethod.GET, value="/clientes/{id}")
	public ResponseEntity<Cliente> find(@PathVariable Integer id) {
		Cliente cliente = clienteService.buscarPorId(id);
		return ResponseEntity.ok().body(cliente);
	}
	//@PreAuthorize("hasAnyRole('ADMIN')") 
	@RequestMapping(method=RequestMethod.GET, value="/clientes/email")
	public ResponseEntity<Cliente> findByEmailLogedUser(@RequestParam(value="email") String email){
		Cliente cliente = clienteService.buscarPorEmailUsuarioLogado(email);
		return ResponseEntity.ok().body(cliente);
	}
	
	
	@RequestMapping(method=RequestMethod.PUT, value="/clientes/{id}")
	public ResponseEntity<Void> update(@RequestBody @Valid ClienteDTO categoriaDTO, @PathVariable Integer id) {
		Cliente cliente = clienteService.fromDTO(categoriaDTO);
		cliente.setId(id);
		clienteService.atualizar(cliente);
		return ResponseEntity.noContent().build();
	}

	
	@RequestMapping(method=RequestMethod.POST, value="/clientes")
	public ResponseEntity<Void> insert(@Valid @RequestBody ClienteNovoDTO categoriaNovoDTO) {
		Cliente clienteDTO = clienteService.fromDTO(categoriaNovoDTO);
		 clienteService.salvarUm(clienteDTO);
		 
		 URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				 .buildAndExpand(clienteDTO.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	@PreAuthorize("hasAnyRole('ADMIN')") 
	@RequestMapping(method=RequestMethod.DELETE, value="/clientes/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		clienteService.deletar(id);
		return ResponseEntity.noContent().build();
	}
	@PreAuthorize("hasAnyRole('ADMIN')") 
	@RequestMapping(method=RequestMethod.GET, value="/clientes")
	public ResponseEntity<List<ClienteDTO>> findAll() {
		List<Cliente> clientes = clienteService.buscarTodos();
		List<ClienteDTO> clientesDTO = clientes.stream().map(object -> new ClienteDTO(object)).collect(Collectors.toList());
		return ResponseEntity.ok().body(clientesDTO);
	}
	@PreAuthorize("hasAnyRole('ADMIN')") 
	@RequestMapping(method=RequestMethod.GET, value="/clientes/page")
	public ResponseEntity<Page<ClienteDTO>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page,
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage,
			@RequestParam(value="orderBy", defaultValue="nome") String orderBy,
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		Page<Cliente> clientes = clienteService.buscarPorPagina(page, linesPerPage, orderBy, direction);
		Page<ClienteDTO> clientesDTO = clientes.map(object -> new ClienteDTO(object));
		return ResponseEntity.ok().body(clientesDTO);
	}

	@RequestMapping(method=RequestMethod.POST, value="/clientes/foto")
	public ResponseEntity<Void> uploadProfilePicture(@RequestParam(name="file") MultipartFile file) {
		URI uri = clienteService.enviarFotoParaPerfil(file);
		return ResponseEntity.created(uri).build();
	}	

}






