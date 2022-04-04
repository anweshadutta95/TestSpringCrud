package com.springboot.restproject.cruddemo.exception;

public class CollegeNotFoundException extends RuntimeException{

	public CollegeNotFoundException() {
	}

	public CollegeNotFoundException(String message) {
		super(message);
	}

	public CollegeNotFoundException(Throwable cause) {
		super(cause);
	}

	public CollegeNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public CollegeNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
