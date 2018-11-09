package br.com.workmade.cursomc.service.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;

import br.com.workmade.cursomc.util.StandardError;

public interface IResourcesExceptionHandler {

	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request);
	public ResponseEntity<StandardError> constraintViolation(DataIntegrityException e, HttpServletRequest request);
	ResponseEntity<StandardError> argumentNotValidException(MethodArgumentNotValidException e,
			HttpServletRequest request);
}
