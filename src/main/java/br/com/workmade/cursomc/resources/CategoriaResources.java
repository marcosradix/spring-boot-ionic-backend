package br.com.workmade.cursomc.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.workmade.cursomc.domain.Categoria;
import br.com.workmade.cursomc.service.CategoriaService;

@RestController
@RequestMapping(value={"/"})
@CrossOrigin(origins = "*")
public class CategoriaResources {
	
	@Autowired
	private CategoriaService categoriaService;
	
	/*@Autowired
	private ProdutoService produtoService;*/
	
	
	@RequestMapping(method=RequestMethod.GET)
	public String teste() {
		return "Testado.";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/categorias/{id}")
	public ResponseEntity<Categoria> find(@PathVariable Integer id) {
		Categoria categoria = categoriaService.buscarPorId(id);
		return ResponseEntity.ok().body(categoria);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/maisdeumacategorias")
	public ResponseEntity<List<Categoria>> saveAll(@RequestBody List<Categoria> categorias) {
		 categoriaService.salvarTodos(categorias);
		return ResponseEntity.ok().body(categorias);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/categorias")
	public ResponseEntity<Void> insert(@RequestBody Categoria categoria) {
		 categoriaService.salvarUm(categoria);
		 URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				 .buildAndExpand(categoria.getId())
				 .toUri();
		return ResponseEntity.created(uri).build();
	}

}
