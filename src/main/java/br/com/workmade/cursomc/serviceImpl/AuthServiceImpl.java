package br.com.workmade.cursomc.serviceImpl;

import java.io.Serializable;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.workmade.cursomc.domain.Cliente;
import br.com.workmade.cursomc.service.AuthService;
import br.com.workmade.cursomc.service.ClienteService;
import br.com.workmade.cursomc.service.EmailService;

@Service
public class AuthServiceImpl implements AuthService, Serializable {

	private static final long serialVersionUID = 5710183372809700253L;

	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private EmailService emailService;

	
	private Random random = new Random();
	
	@Override
	public void enviarNovaSenha(String email) {
		Cliente cliente = clienteService.buscarPorEmail(email); 
		String novaSenha = gerarNovaSenha();
		cliente.setSenha(bCryptPasswordEncoder.encode(novaSenha));
		clienteService.salvarComNovaSenha(cliente);
		emailService.enviarNovaSenhaDeEmail(cliente, novaSenha);
	}

	private String gerarNovaSenha() {
		char[] vet = new char[10];
		for (int i = 0; i < vet.length; i++) {
			vet[i] = caracterAleatorio();
		}
		return new String(vet);
	}

	private char caracterAleatorio() {
		int optCase = this.random.nextInt(3);
		char aux;
		switch (optCase) {
		case 0: //gera um digito
			aux = (char) (random.nextInt(10) + 48);
			break;
			
		case 1: //gera letra uperCase
			aux = (char) (random.nextInt(26) + 65);
			break;
			
		default: //gera letra lowerCase
		case 2: 
			aux = (char) (random.nextInt(10) + 97);
			break;
		}

		return aux;
	}

}
