package com.sanada.error;

public class UserNotAuthorizedException extends RuntimeException {
	
	public UserNotAuthorizedException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserNotAuthorizedException(String message) {
		super(message);
	}

	public UserNotAuthorizedException(Throwable cause) {
		super(cause);
	}
	
}
