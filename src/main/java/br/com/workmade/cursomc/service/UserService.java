package br.com.workmade.cursomc.service;

import org.springframework.security.core.context.SecurityContextHolder;

import br.com.workmade.cursomc.security.UserSS;

public interface UserService {
	
	public static UserSS authenticated() {
		try {
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			return null;
		}
		
	}

}
