package br.com.workmade.cursomc.service;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import br.com.workmade.cursomc.domain.Cliente;
import br.com.workmade.cursomc.domain.Pedido;

public interface EmailService {

	public void emailDeConfirmacaoDePedido(Pedido pedido) ;
	
	public void enviarEmail(SimpleMailMessage msg);
	
	public void emailDeConfirmacaoDePedidoHtml(Pedido obj);
	
	public void enviarEmailHtml(MimeMessage msg);
	
	public void enviarNovaSenhaDeEmail(Cliente cliente, String novaSenha);
}
