package br.com.workmade.cursomc.service.exceptions;

public class AuthorizationException extends RuntimeException {

	private static final long serialVersionUID = -3424275504562355933L;

	
	public AuthorizationException(String msg) {
		super(msg);
	}
	
	public AuthorizationException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
}
