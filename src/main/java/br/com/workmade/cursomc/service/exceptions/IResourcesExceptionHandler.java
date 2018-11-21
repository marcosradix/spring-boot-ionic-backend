package br.com.workmade.cursomc.service.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.model.AmazonS3Exception;

import br.com.workmade.cursomc.util.StandardError;

public interface IResourcesExceptionHandler {

	ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request);
	ResponseEntity<StandardError> constraintViolation(DataIntegrityException e, HttpServletRequest request);
	ResponseEntity<StandardError> argumentNotValidException(MethodArgumentNotValidException e,
			HttpServletRequest request);
	ResponseEntity<StandardError> authorization(AuthorizationException e, HttpServletRequest request);
	ResponseEntity<StandardError> file(FileException e, HttpServletRequest request);
	ResponseEntity<StandardError> amazonService(AmazonServiceException e, HttpServletRequest request);
	ResponseEntity<StandardError> amzonClient(AmazonClientException e, HttpServletRequest request);
	ResponseEntity<StandardError> amzonS3(AmazonS3Exception e, HttpServletRequest request);
}
