package com.qsp.springbootCompany.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class CompanyExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<String> handleIdNotFoundException(IdNotFoundException exception) {
		return new ResponseEntity<String>(exception.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(NameNotFoundException.class)
	public ResponseEntity<String> handleNameNotFoundException(NameNotFoundException exception) {

		return new ResponseEntity<String>(exception.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(LocationNotFoundException.class)
	public ResponseEntity<String> handleLocationNotFoundException(LocationNotFoundException exception) {
		return new ResponseEntity<String>(exception.getMessage(),HttpStatus.NOT_FOUND);
	}
}
