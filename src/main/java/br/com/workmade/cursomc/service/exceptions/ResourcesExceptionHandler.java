package br.com.workmade.cursomc.service.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.model.AmazonS3Exception;

import br.com.workmade.cursomc.util.StandardError;
import br.com.workmade.cursomc.util.ValidationError;

@ControllerAdvice
public class ResourcesExceptionHandler implements IResourcesExceptionHandler{

	@ExceptionHandler(ObjectNotFoundException.class)
	@Override
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {
		StandardError error = new StandardError(
				HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}

	
	@ExceptionHandler(AuthorizationException.class)
	@Override
	public ResponseEntity<StandardError> authorization(AuthorizationException e, HttpServletRequest request) {
		StandardError error = new StandardError(
				HttpStatus.FORBIDDEN.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
	}
	
	
	@ExceptionHandler(DataIntegrityException.class)
	@Override
	public ResponseEntity<StandardError> constraintViolation(DataIntegrityException e,
			HttpServletRequest request) {
		StandardError error = new StandardError(
				HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}

	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@Override
	public ResponseEntity<StandardError> argumentNotValidException(MethodArgumentNotValidException e,
			HttpServletRequest request) {
		ValidationError error = new ValidationError(
				HttpStatus.BAD_REQUEST.value(), "Erro de validação.", System.currentTimeMillis());
		
		for (FieldError errorX : e.getBindingResult().getFieldErrors()) {
			error.addError(errorX.getField(), errorX.getDefaultMessage());
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
	@ExceptionHandler(FileException.class)
	@Override
	public ResponseEntity<StandardError> file(FileException e, HttpServletRequest request) {
		StandardError error = new StandardError(
				HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
	@ExceptionHandler(AmazonServiceException.class)
	@Override
	public ResponseEntity<StandardError> amazonService(AmazonServiceException e, HttpServletRequest request) {
		HttpStatus code = HttpStatus.valueOf(e.getErrorCode());
		
		StandardError error = new StandardError(
				code.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(code).body(error);
	}
	
	@ExceptionHandler(AmazonClientException.class)
	@Override
	public ResponseEntity<StandardError> amzonClient(AmazonClientException e, HttpServletRequest request) {
		StandardError error = new StandardError(
				HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
	@ExceptionHandler(AmazonS3Exception.class)
	@Override
	public ResponseEntity<StandardError> amzonS3(AmazonS3Exception e, HttpServletRequest request) {
		StandardError error = new StandardError(
				HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
}
