package br.com.workmade.cursomc.util;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError{

	private static final long serialVersionUID = -3746190989102804445L;

	private List<FieldMessage> listErrors = new ArrayList<>();
	
	public ValidationError(Integer status, String message, Long timeStamp) {
		super(status, message, timeStamp);
	}

	public List<FieldMessage> getListErrors() {
		return listErrors;
	}

	public void addError(String fieldName ,  String message) {
		this.listErrors.add(new FieldMessage(fieldName, message));
	}
	
	

}
