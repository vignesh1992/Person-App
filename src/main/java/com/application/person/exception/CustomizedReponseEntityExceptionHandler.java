package com.application.person.exception;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.validation.ConstraintViolationException;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.application.person.model.ErrorDetails;

@ControllerAdvice
@Order(value = Ordered.LOWEST_PRECEDENCE)
public class CustomizedReponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	protected static final Logger logger = Logger.getLogger(CustomizedReponseEntityExceptionHandler.class.getName());

	@ExceptionHandler(BadCredentialsException.class)
	public final ResponseEntity<ErrorDetails> constrainViolationException(BadCredentialsException ex,
			WebRequest request) {
		logger.info("inside BadCredentialsException");
		logger.log(Level.SEVERE, ex.getMessage(), ex);
		Date date = new Date();
		ErrorDetails errorDetails = new ErrorDetails(date, ex.getMessage(), request.getDescription(false),
				ex.getLocalizedMessage());
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public final ResponseEntity<ErrorDetails> constrainViolationException(ConstraintViolationException ex,
			WebRequest request) {
		logger.info("inside ConstraintViolationException");
		logger.log(Level.SEVERE, ex.getMessage(), ex);
		Date date = new Date();
		ErrorDetails errorDetails = new ErrorDetails(date, ex.getMessage(), request.getDescription(false),
				ex.getLocalizedMessage());
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.CONFLICT);
	}

	@ExceptionHandler(AccessDeniedException.class)
	public final ResponseEntity<ErrorDetails> constrainViolationException(AccessDeniedException ex,
			WebRequest request) {
		logger.info("inside AccessDeniedException");
		logger.log(Level.SEVERE, ex.getMessage(), ex);
		Date date = new Date();
		ErrorDetails errorDetails = new ErrorDetails(date, ex.getMessage(), request.getDescription(false),
				ex.getLocalizedMessage());
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler(PersonNotFoundException.class)
	public final ResponseEntity<ErrorDetails> personNotFoundExceptions(PersonNotFoundException ex, WebRequest request) {
		logger.info("inside PersonNotFoundException");
		logger.log(Level.SEVERE, ex.getMessage(), ex);
		Date date = new Date();
		ErrorDetails errorDetails = new ErrorDetails(date, ex.getMessage(), request.getDescription(false),
				ex.getLocalizedMessage());
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(HttpClientErrorException.class)
	public final ResponseEntity<ErrorDetails> handleHttpClientErrorExceptions(HttpClientErrorException ex,
			WebRequest request) {
		logger.info("inside HttpClientErrorException");
		logger.log(Level.SEVERE, ex.getMessage(), ex);
		HttpStatus status = ex.getStatusCode();
		Date date = new Date();
		ErrorDetails errorDetails = new ErrorDetails(date, ex.getMessage(), request.getDescription(false),
				ex.getResponseBodyAsString());
		return new ResponseEntity<ErrorDetails>(errorDetails, status);
	}

	@ExceptionHandler(HttpServerErrorException.class)
	public final ResponseEntity<ErrorDetails> handleHttpServerErrorException(HttpServerErrorException ex,
			WebRequest request) {
		logger.info("inside HttpServerErrorException");
		logger.log(Level.SEVERE, ex.getMessage(), ex);
		HttpStatus status = ex.getStatusCode();
		Date date = new Date();
		ErrorDetails errorDetails = new ErrorDetails(date, ex.getMessage(), request.getDescription(false),
				ex.getResponseBodyAsString());
		return new ResponseEntity<ErrorDetails>(errorDetails, status);
	}

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorDetails> handleAllExceptions(Exception ex, WebRequest request) {
		logger.info("inside general Exception");
		logger.log(Level.SEVERE, ex.getMessage(), ex);
		Date date = new Date();
		ErrorDetails errorDetails = new ErrorDetails(date, ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
