package br.com.workmade.cursomc.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import br.com.workmade.cursomc.domain.Cliente;
import br.com.workmade.cursomc.dto.ClienteDTO;
import br.com.workmade.cursomc.service.ClienteService;

public class ClienteUpdateValidatorTask implements ConstraintValidator<ClienteUpdateValidation, ClienteDTO>{

	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private HttpServletRequest request;
	
	@Override
	public void initialize(ClienteUpdateValidation constraintAnnotation) {}
	
	@Override
	public boolean isValid(ClienteDTO clienteDTO, ConstraintValidatorContext context) {
		//pegar id da URI da requisição
		System.out.println("Erro "+ClienteUpdateValidatorTask.class);
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Integer idRequest = Integer.parseInt(map.get("id"));
		List<FieldMessage> list = new ArrayList<>();
	
		Cliente clienteBanco = clienteService.buscarPorEmail(clienteDTO.getEmail());
			if(clienteBanco != null && !clienteBanco.getId().equals(idRequest)) {
				list.add(new FieldMessage("email", "E-mail já existente."));
			}
		for (FieldMessage fieldMessage : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(fieldMessage.getMessage())
			.addPropertyNode(fieldMessage.getFieldName()).addConstraintViolation();
		}
		return list.isEmpty();
	}

}
