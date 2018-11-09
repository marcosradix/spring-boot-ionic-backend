package br.com.workmade.cursomc.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.workmade.cursomc.domain.Categoria;
import br.com.workmade.cursomc.dto.CategoriaDTO;
import br.com.workmade.cursomc.repositories.CategoriaRepository;
import br.com.workmade.cursomc.service.CategoriaService;
import br.com.workmade.cursomc.service.exceptions.DataIntegrityException;
import br.com.workmade.cursomc.service.exceptions.ObjectNotFoundException;

@Service
public class CategoriaServiceImpl implements CategoriaService {
	
	private Logger LOGGER = Logger.getLogger(CategoriaServiceImpl.class);
	@Autowired
	private CategoriaRepository categoriaRepository; 
	
	
	@Override
	public Categoria buscarPorId(Integer id){
		Optional<Categoria> categoria = categoriaRepository.findById(id); 
		return categoria.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id : "+id+" : "+ Categoria.class.getName()));
	}


	@Override
	public List<Categoria> salvarTodos(List<Categoria> categoria) {
		return categoriaRepository.saveAll(categoria);
	}


	@Override
	public Categoria salvarUm(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}


	@Override
	public Categoria atualizar(Categoria categoria) {
		buscarPorId(categoria.getId());
		return categoriaRepository.save(categoria);
	}


	@Override
	public void deletar(Integer id){
		buscarPorId(id);
		try {
			categoriaRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			LOGGER.error("Saida de erro : "+e);
			
			throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos");
		}
	
		
	}


	@Override
	public List<Categoria> buscarTodos() {
		return categoriaRepository.findAll();
	}


	@Override
	public Page<Categoria> buscarPorPagina(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return categoriaRepository.findAll(pageRequest);
	}


	@Override
	public Categoria fromDTO(CategoriaDTO categoriaDTO) {
		return new Categoria(categoriaDTO.getId(), categoriaDTO.getNome());
	}

}
