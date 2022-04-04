package com.springboot.restproject.cruddemo.exception;

public class FacultyNotFoundException extends RuntimeException{

	public FacultyNotFoundException() {
	}

	public FacultyNotFoundException(String message) {
		super(message);
	}

	public FacultyNotFoundException(Throwable cause) {
		super(cause);
	}

	public FacultyNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public FacultyNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
