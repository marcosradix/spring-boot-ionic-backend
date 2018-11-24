package br.com.workmade.cursomc.resources;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.workmade.cursomc.dto.EmailDTO;
import br.com.workmade.cursomc.security.JWTUtil;
import br.com.workmade.cursomc.security.UserSS;
import br.com.workmade.cursomc.service.AuthService;
import br.com.workmade.cursomc.service.UserService;
@RestController
@RequestMapping(value={"/auth"})
public class AuthResource {

	@Autowired
	private JWTUtil jwtUtil;
	
	@Autowired
	private AuthService authService;
	
	@RequestMapping(value = "/refresh_token", method = RequestMethod.POST)
	public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
		UserSS user = UserService.authenticated();
		String token = jwtUtil.generateToken(user.getUsername());
		response.addHeader("Authorization", "Bearer " + token);
		response.addHeader("access-control-expose-headers", "Authorization");
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/forgot", method = RequestMethod.POST)
	public ResponseEntity<Void> esqueciMinhasenha(@RequestBody @Valid EmailDTO emailDTO) {
		authService.enviarNovaSenha(emailDTO.getEmail());
		return ResponseEntity.noContent().build();
	}

}
