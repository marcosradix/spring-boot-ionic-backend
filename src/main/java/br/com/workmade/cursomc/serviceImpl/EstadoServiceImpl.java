package br.com.workmade.cursomc.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.workmade.cursomc.domain.Estado;
import br.com.workmade.cursomc.repositories.EstadoRepository;
import br.com.workmade.cursomc.service.EstadoService;
@Service
public class EstadoServiceImpl implements EstadoService {
	
	
	@Autowired
	private EstadoRepository estadoRepository;

	@Override
	public Estado salvar(Estado estado) {
		return estadoRepository.save(estado);
	}

	@Override
	public List<Estado> salvarTodos(List<Estado> estados) {
		return estadoRepository.saveAll(estados);
	}

	@Override
	public List<Estado> buscarEstados() {
		return estadoRepository.findAllByOrderByNome();
	}

}
