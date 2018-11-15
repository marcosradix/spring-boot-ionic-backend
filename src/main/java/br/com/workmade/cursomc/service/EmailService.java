package br.com.workmade.cursomc.service;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import br.com.workmade.cursomc.domain.Pedido;

public interface EmailService {

	void emailDeConfirmacaoDePedido(Pedido pedido) ;
	
	void enviarEmail(SimpleMailMessage msg);
	
	void emailDeConfirmacaoDePedidoHtml(Pedido obj);
	
	void enviarEmailHtml(MimeMessage msg);
}
