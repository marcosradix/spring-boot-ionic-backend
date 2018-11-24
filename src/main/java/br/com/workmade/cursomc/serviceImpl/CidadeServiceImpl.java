package br.com.workmade.cursomc.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.workmade.cursomc.domain.Cidade;
import br.com.workmade.cursomc.repositories.CidadeRepository;
import br.com.workmade.cursomc.service.CidadeService;
import br.com.workmade.cursomc.service.exceptions.ObjectNotFoundException;
@Service
public class CidadeServiceImpl implements CidadeService {

	@Autowired
	private CidadeRepository cidaderepository;
	
	
	@Override
	public Cidade salvar(Cidade cidade) {
		return cidaderepository.save(cidade);
	}


	@Override
	public List<Cidade> salvarTodos(List<Cidade> cidades) {
		return cidaderepository.saveAll(cidades);
	}
	
	@Override
	public List<Cidade> listarCidadesPorIdEstado(Integer estadoId) {
		Optional<List<Cidade>> cidades =  cidaderepository.findAllByEstadoIdOrderByNome(estadoId);
		return cidades.orElseThrow(() -> new ObjectNotFoundException(
				"estado não encontrado com ID : "+estadoId));
	}

}
