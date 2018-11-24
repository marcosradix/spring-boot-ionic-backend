package br.com.workmade.cursomc.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.workmade.cursomc.domain.Cidade;
import br.com.workmade.cursomc.domain.Estado;
import br.com.workmade.cursomc.dto.CidadeDTO;
import br.com.workmade.cursomc.dto.EstadoDTO;
import br.com.workmade.cursomc.service.CidadeService;
import br.com.workmade.cursomc.service.EstadoService;

@RestController
@RequestMapping(value="/estados")
public class EstadoResources {
	
	@Autowired
	private EstadoService estadoService;
	
	@Autowired
	private CidadeService cidadeService;
	
	
	@RequestMapping(method= RequestMethod.GET)
	public ResponseEntity< List<EstadoDTO> > listEstados(){
		List<Estado> list = estadoService.buscarEstados();
		List<EstadoDTO> listDTO = list.stream().map(estado -> new EstadoDTO(estado)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
		
	}
	
	@RequestMapping(method= RequestMethod.GET, value="/{estadoId}/cidades")
	public ResponseEntity< List<CidadeDTO> > listCidadesPorEstados(@PathVariable Integer estadoId){
		List<Cidade> list = cidadeService.listarCidadesPorIdEstado(estadoId);
		List<CidadeDTO> listDTO = list.stream().map(cidade -> new CidadeDTO(cidade)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
		
	}

}
