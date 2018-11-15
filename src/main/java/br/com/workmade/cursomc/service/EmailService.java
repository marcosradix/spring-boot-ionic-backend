package br.com.workmade.cursomc.service;

import org.springframework.mail.SimpleMailMessage;

import br.com.workmade.cursomc.domain.Pedido;

public interface EmailService {

	void emailDeConfirmacaoDePedido(Pedido pedido);
	
	void enviarEmail(SimpleMailMessage msg);
}
