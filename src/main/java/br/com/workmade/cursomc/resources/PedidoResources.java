package br.com.workmade.cursomc.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.workmade.cursomc.domain.Pedido;
import br.com.workmade.cursomc.service.PedidoService;

@RestController
@RequestMapping(value={"/"})
public class PedidoResources {


	@Autowired
	private PedidoService pedidoService;
	
	@RequestMapping(method=RequestMethod.GET, value="/pedidos/{id}")
	public ResponseEntity<Pedido> find(@PathVariable Integer id) {
		Pedido pedido = pedidoService.buscarPorId(id);
		return ResponseEntity.ok().body(pedido);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/pedidos")
	public ResponseEntity<Void> insert(@RequestBody @Valid Pedido pedido) {
		 pedidoService.salvarUm(pedido);
		 URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				 .buildAndExpand(pedido.getId())
				 .toUri();
		return ResponseEntity.created(uri).build();
	}
}
