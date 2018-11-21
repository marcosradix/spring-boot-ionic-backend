package br.com.workmade.cursomc.service.exceptions;

public class FileException extends RuntimeException {

	private static final long serialVersionUID = -3424275504562355933L;

	
	public FileException(String msg) {
		super(msg);
	}
	
	public FileException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
}
