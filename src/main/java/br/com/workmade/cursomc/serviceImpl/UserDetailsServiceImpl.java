package br.com.workmade.cursomc.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.workmade.cursomc.domain.Cliente;
import br.com.workmade.cursomc.security.UserSS;
import br.com.workmade.cursomc.service.ClienteService;
@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private ClienteService clienteService;
	

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Cliente  cliente  = clienteService.buscarPorEmail(email);;
			if(cliente == null) {
				throw new UsernameNotFoundException(email);
			}
			
		
		return new UserSS(cliente.getId(), cliente.getEmail(), cliente.getSenha(), cliente.getPerfis());
	}

}
