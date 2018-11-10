package br.com.workmade.cursomc.util;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.workmade.cursomc.domain.Cliente;
import br.com.workmade.cursomc.domain.enums.TipoCliente;
import br.com.workmade.cursomc.dto.ClienteNovoDTO;
import br.com.workmade.cursomc.service.ClienteService;

public class ClienteInsertValidatorTask implements ConstraintValidator<ClienteInsertValidation, ClienteNovoDTO>{

	@Autowired
	private ClienteService clienteService;
	
	@Override
	public void initialize(ClienteInsertValidation constraintAnnotation) {}
	
	@Override
	public boolean isValid(ClienteNovoDTO clienteNovoDTO, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
	
		if(clienteNovoDTO.getTipo().equals(TipoCliente.PESSOA_FISICA.getCod())  && !BR.isValidCPF(clienteNovoDTO.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "Cpf inválido!"));
		}
		
		if(clienteNovoDTO.getTipo().equals(TipoCliente.PESSOA_JURIDICA.getCod())  && !BR.isValidCNPJ(clienteNovoDTO.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido!"));
		}
		
		Cliente emailExists = clienteService.buscarPorEmail(clienteNovoDTO.getEmail());
			if(emailExists != null) {
				list.add(new FieldMessage("email", "E-mail já cadastrado."));
			}
		for (FieldMessage fieldMessage : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(fieldMessage.getMessage())
			.addPropertyNode(fieldMessage.getFieldName()).addConstraintViolation();
		}
		return list.isEmpty();
	}

}
