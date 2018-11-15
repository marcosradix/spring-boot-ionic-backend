package br.com.workmade.cursomc.serviceImpl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import br.com.workmade.cursomc.domain.Pedido;
import br.com.workmade.cursomc.service.EmailService;

public abstract class AbstractEmailServiceImpl implements EmailService {

	@Value("${default.email.para}")
	private String emailFrom;
	@Override
	public void emailDeConfirmacaoDePedido(Pedido pedido) {
		SimpleMailMessage smm = prepararSimplesMenssagemDeEmailDePedido(pedido); 
		enviarEmail(smm);
	}

	protected SimpleMailMessage prepararSimplesMenssagemDeEmailDePedido(Pedido pedido) {
		SimpleMailMessage smm = new  SimpleMailMessage();
		smm.setTo(pedido.getCliente().getEmail());
		smm.setFrom(emailFrom);
		smm.setSubject("Pedido confirmado : "+pedido.getId());
		smm.setSentDate(new Date(System.currentTimeMillis()));
		smm.setText(pedido.toString());
		return smm;
	}
	
}
