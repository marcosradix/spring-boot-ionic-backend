package br.com.workmade.cursomc.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.workmade.cursomc.domain.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
	
	@Transactional(readOnly=true)
	public Optional<Cliente> findByEmail(String email);

	/*@Query(name="select * from CLIENTE where email=:email",nativeQuery=true)
	public Cliente buscarClientePorUsuarioLogado(@Param("email") String email);*/

}
