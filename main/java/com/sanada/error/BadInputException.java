package com.sanada.error;

public class BadInputException extends RuntimeException {
	
	public BadInputException(String message, Throwable cause) {
		super(message, cause);
	}

	public BadInputException(String message) {
		super(message);
	}

	public BadInputException(Throwable cause) {
		super(cause);
	}
}
