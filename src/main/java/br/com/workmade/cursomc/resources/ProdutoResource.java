package br.com.workmade.cursomc.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.workmade.cursomc.domain.Produto;
import br.com.workmade.cursomc.dto.ProdutoDTO;
import br.com.workmade.cursomc.service.ProdutoService;
import br.com.workmade.cursomc.util.URL;

@RestController
@RequestMapping(value={"/"})
@CrossOrigin(origins = "*")
public class ProdutoResource {


	@Autowired
	private ProdutoService produtoService;
	
	@RequestMapping(method=RequestMethod.GET, value="/produtos/{id}")
	public ResponseEntity<Produto> find(@PathVariable Integer id) {
		Produto produto = produtoService.buscarPorId(id);
		return ResponseEntity.ok().body(produto);
	}
	
	
	@RequestMapping(method=RequestMethod.GET, value="/produtos")
	public ResponseEntity<Page<ProdutoDTO>> findPage(
			@RequestParam(value="nome", defaultValue="") String nome,
			@RequestParam(value="categorias", defaultValue="") String categorias,
			@RequestParam(value="page", defaultValue="0") Integer page,
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage,
			@RequestParam(value="orderBy", defaultValue="nome") String orderBy,
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		String nomeDecoded = URL.decodeParam(nome);
		List<Integer> ids = URL.transformarStringEmArrayDeInteger(categorias);
		Page<Produto> categoriasList = produtoService.search(nomeDecoded, ids ,page, linesPerPage, orderBy, direction);
		Page<ProdutoDTO> categoriasDTO = categoriasList.map(object -> new ProdutoDTO(object));
		return ResponseEntity.ok().body(categoriasDTO);
	}
}









