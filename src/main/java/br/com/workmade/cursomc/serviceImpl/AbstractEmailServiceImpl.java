package br.com.workmade.cursomc.serviceImpl;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import br.com.workmade.cursomc.domain.Pedido;
import br.com.workmade.cursomc.service.EmailService;

public abstract class AbstractEmailServiceImpl implements EmailService {

	@Value("${default.email.para}")
	private String emailFrom;
	
	@Autowired
	private TemplateEngine templateEngine;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	
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
	

	protected String htmlFromTemplatePedido(Pedido pedido)  {
		Context context = new Context();
		context.setVariable("pedido", pedido);
		return templateEngine.process("email/confirmacaoPedido", context);
		
	}
	protected MimeMessage prepararMimeMenssagemDeEmailDePedido(Pedido pedido) throws MessagingException {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
	
			MimeMessageHelper mimeMessageHelper  = new MimeMessageHelper(mimeMessage, true);
			mimeMessageHelper.setTo(pedido.getCliente().getEmail());
			mimeMessageHelper.setFrom(emailFrom);
			mimeMessageHelper.setSubject("Pedido confirmado "+pedido.getId());
			mimeMessageHelper.setSentDate(new Date(System.currentTimeMillis()));
			mimeMessageHelper.setText(htmlFromTemplatePedido(pedido), true);

		return mimeMessage;
		
	}
	
	@Override
	public void emailDeConfirmacaoDePedidoHtml(Pedido pedido) {
		try {
			MimeMessage mms = prepararMimeMenssagemDeEmailDePedido(pedido);
			enviarEmailHtml(mms);
	} catch (MessagingException e) {
		emailDeConfirmacaoDePedido(pedido);
	} 

		
	}
}
