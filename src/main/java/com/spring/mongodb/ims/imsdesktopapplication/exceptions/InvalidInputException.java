package com.spring.mongodb.ims.imsdesktopapplication.exceptions;

//refactor to InvalidInputException
public class InvalidInputException extends RuntimeException{

	private static final long serialVersionUID = -5209677727536961345L;
	
	public InvalidInputException(String message) {
		super(message);
	}

}
