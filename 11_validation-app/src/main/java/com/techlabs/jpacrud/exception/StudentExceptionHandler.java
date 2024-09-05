package com.techlabs.jpacrud.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.techlabs.jpacrud.error.StudentErrorResponse;

@ControllerAdvice
public class StudentExceptionHandler {
	
	@ExceptionHandler
	public ResponseEntity<StudentErrorResponse> handleStudentDoesNotExistException(StudentDoesNotExistException exception){
		StudentErrorResponse error = new StudentErrorResponse();
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setErrorMessage(exception.getMessage());
		error.setTimestamp(System.currentTimeMillis());
		return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
		
	}
	

	@ExceptionHandler
	public ResponseEntity<StudentErrorResponse> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException exception){
		StudentErrorResponse error = new StudentErrorResponse();
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setErrorMessage("rollnumber can be integer only");
		error.setTimestamp(System.currentTimeMillis());
		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
		
	}
	

	@ExceptionHandler
	 
	    protected ResponseEntity<Object> handleMethodArgumentNotValid(
	        MethodArgumentNotValidException ex) {
	        StudentErrorResponse error = new StudentErrorResponse();
	        error.setStatus(HttpStatus.BAD_REQUEST.value());
	        error.setErrorMessage("Validation failed");
	        error.setTimestamp(System.currentTimeMillis());
	        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	    }

	

}
