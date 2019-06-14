package com.sanada;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.sanada.error.BadInputException;
import com.sanada.error.MessageError;
import com.sanada.error.ProductNotFoundException;
import com.sanada.error.UserNotAuthorizedException;
import com.sanada.error.UserNotFoundException;

@ControllerAdvice
public class RestExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<MessageError> handleException(UserNotFoundException ex){
		
		MessageError error= new MessageError();
				
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setMessage(ex.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
	
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);

	}
	
	@ExceptionHandler
	public ResponseEntity<MessageError> handleException(UserNotAuthorizedException ex){
		
		MessageError error= new MessageError();
		
		error.setStatus(HttpStatus.UNAUTHORIZED.value());
		error.setMessage(ex.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler
	public ResponseEntity<MessageError> handleException(ProductNotFoundException ex){
		
		MessageError error= new MessageError();
		
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setMessage(ex.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler
	public ResponseEntity<MessageError> handleException(BadInputException ex){
		
		MessageError error= new MessageError();
		
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setMessage(ex.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler
	public ResponseEntity<MessageError> handleException(Exception ex){
		
		// create a StudentErrorResponse
		
		MessageError error= new MessageError();
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setMessage(ex.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		
		// return ResponseEntity		
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	
}
