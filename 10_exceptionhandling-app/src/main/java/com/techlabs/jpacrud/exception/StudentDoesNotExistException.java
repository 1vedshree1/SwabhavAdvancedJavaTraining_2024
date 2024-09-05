package com.techlabs.jpacrud.exception;

public class StudentDoesNotExistException extends RuntimeException{
	
	public String getMessage() {
		return "student does not exist";
	}
	
	

}
