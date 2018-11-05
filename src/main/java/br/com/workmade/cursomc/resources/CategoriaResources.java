package br.com.workmade.cursomc.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.workmade.cursomc.domain.Categoria;
import br.com.workmade.cursomc.dto.CategoriaDTO;
import br.com.workmade.cursomc.service.CategoriaService;

@RestController
@RequestMapping(value={"/"})
@CrossOrigin(origins = "*")
public class CategoriaResources {
	
	@Autowired
	private CategoriaService categoriaService;
	
	
	@RequestMapping(method=RequestMethod.GET)
	public String teste() {
		return "Testado.";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/categorias/{id}")
	public ResponseEntity<Categoria> find(@PathVariable Integer id) {
		Categoria categoria = categoriaService.buscarPorId(id);
		return ResponseEntity.ok().body(categoria);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/categorias/{id}")
	public ResponseEntity<Void> update(@RequestBody Categoria categoria, @PathVariable Integer id) {
		categoria.setId(id);
		categoriaService.atualizar(categoria);
		return ResponseEntity.noContent().build();
	}

	
	@RequestMapping(method=RequestMethod.POST, value="/categorias")
	public ResponseEntity<Void> insert(@RequestBody Categoria categoria) {
		 categoria.setId(null);
		 categoriaService.salvarUm(categoria);
		 URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				 .buildAndExpand(categoria.getId())
				 .toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/categorias/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		categoriaService.deletar(id);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(method=RequestMethod.GET, value="/categorias")
	public ResponseEntity<List<CategoriaDTO>> findAll() {
		List<Categoria> categorias = categoriaService.buscarTodos();
		List<CategoriaDTO> categoriasDTO = categorias.stream().map(object -> new CategoriaDTO(object)).collect(Collectors.toList());
		return ResponseEntity.ok().body(categoriasDTO);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/categorias/page")
	public ResponseEntity<Page<CategoriaDTO>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page,
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage,
			@RequestParam(value="orderBy", defaultValue="nome") String orderBy,
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		Page<Categoria> categorias = categoriaService.buscarPorPagina(page, linesPerPage, orderBy, direction);
		Page<CategoriaDTO> categoriasDTO = categorias.map(object -> new CategoriaDTO(object));
		return ResponseEntity.ok().body(categoriasDTO);
	}
}


















