package br.com.workmade.cursomc.util;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.workmade.cursomc.domain.enums.TipoCliente;
import br.com.workmade.cursomc.dto.ClienteNovoDTO;

public class ClienteInsertValidatorTask implements ConstraintValidator<ClienteInsertValidation, ClienteNovoDTO>{

	@Override
	public void initialize(ClienteInsertValidation constraintAnnotation) {
		/*ConstraintValidator.super.initialize(constraintAnnotation);*/
	}
	
	@Override
	public boolean isValid(ClienteNovoDTO clienteNovoDTO, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
	
		if(clienteNovoDTO.getTipo().equals(TipoCliente.PESSOA_FISICA.getCod())  && !BR.isValidCPF(clienteNovoDTO.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "Cpf inválido!"));
		}
		
		if(clienteNovoDTO.getTipo().equals(TipoCliente.PESSOA_JURIDICA.getCod())  && !BR.isValidCNPJ(clienteNovoDTO.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido!"));
		}
		
		for (FieldMessage fieldMessage : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(fieldMessage.getMessage())
			.addPropertyNode(fieldMessage.getFieldName()).addConstraintViolation();
		}
		return list.isEmpty();
	}

}
