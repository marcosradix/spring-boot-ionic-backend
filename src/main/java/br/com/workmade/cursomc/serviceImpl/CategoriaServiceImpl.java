package br.com.workmade.cursomc.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.workmade.cursomc.domain.Categoria;
import br.com.workmade.cursomc.repositories.CategoriaRepository;
import br.com.workmade.cursomc.service.CategoriaService;
import br.com.workmade.cursomc.service.exceptions.DataIntegrityException;
import br.com.workmade.cursomc.service.exceptions.ObjectNotFoundException;

@Service
public class CategoriaServiceImpl implements CategoriaService {

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
			throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos");
		}
	
		
	}


	@Override
	public List<Categoria> buscarTodos() {
		return categoriaRepository.findAll();
	}

}
