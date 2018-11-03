package br.com.workmade.cursomc.service.exceptions;

public class DataIntegrityException extends RuntimeException  {

	private static final long serialVersionUID = -2021856089429388218L;

	public DataIntegrityException(String msg) {
		super(msg);
	}
	
	public DataIntegrityException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
}
