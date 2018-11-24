package br.com.workmade.cursomc.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.workmade.cursomc.domain.Cidade;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Integer>{

	//@Query("select obj Cidade obj WHERE obj.estado.id = estadoId ORDER BY obj.nome"
	@Transactional(readOnly=true)
	public Optional<List<Cidade>> findAllByEstadoIdOrderByNome(Integer id);
}
