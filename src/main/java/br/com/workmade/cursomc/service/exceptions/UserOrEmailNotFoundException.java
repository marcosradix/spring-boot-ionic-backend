package br.com.workmade.cursomc.service.exceptions;

public class UserOrEmailNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -3424275504562355933L;

	
	public UserOrEmailNotFoundException(String msg) {
		super(msg);
	}
	
	public UserOrEmailNotFoundException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
}
