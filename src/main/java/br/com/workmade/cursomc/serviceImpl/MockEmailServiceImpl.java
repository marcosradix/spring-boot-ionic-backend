package br.com.workmade.cursomc.serviceImpl;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

public class MockEmailServiceImpl extends AbstractEmailServiceImpl{

	private static final Logger LOGGER = LoggerFactory.getLogger(MockEmailServiceImpl.class);
	
	@Override
	public void enviarEmail(SimpleMailMessage msg) {
		LOGGER.info("Simulando envio de email.......");
		LOGGER.info(msg.toString());
		LOGGER.info("Email enviado.....");
		
	}

	@Override
	public void enviarEmailHtml(MimeMessage msg) {
		LOGGER.info("Simulando envio de email HTML.......");
		LOGGER.info(msg.toString());
		LOGGER.info("Email enviado.....");
		
	}

}
