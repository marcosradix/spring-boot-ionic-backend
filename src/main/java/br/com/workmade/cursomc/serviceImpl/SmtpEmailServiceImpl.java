package br.com.workmade.cursomc.serviceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class SmtpEmailServiceImpl extends AbstractEmailServiceImpl {

	private static final Logger LOGGER = LoggerFactory.getLogger(SmtpEmailServiceImpl.class);
	
	@Autowired
	private MailSender mailSender;
	
	@Override
	public void enviarEmail(SimpleMailMessage msg) {
		LOGGER.info("Envio de email via smtp.......");
		mailSender.send(msg);
		LOGGER.info("Email enviado.....");
		
	}
	
	

	
}
