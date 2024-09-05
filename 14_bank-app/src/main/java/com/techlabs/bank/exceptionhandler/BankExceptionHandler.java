package com.techlabs.bank.exceptionhandler;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.techlabs.bank.error.BankErrorResponse;
import com.techlabs.bank.exception.BaseException;
import com.techlabs.bank.exception.CustomerNotFoundException;
import com.techlabs.bank.exception.IncorrectPasswordException;


@ControllerAdvice
public class BankExceptionHandler {
	
	
	 @ExceptionHandler(BaseException.class)
	    public ResponseEntity<BankErrorResponse> handleBaseException(BaseException ex) {
	        BankErrorResponse error = new BankErrorResponse();
	        error.setStatus(ex.getStatus().value());
	        error.setErrorMessage(ex.getMessage());
	        error.setTimestamp(System.currentTimeMillis());

	        return new ResponseEntity<>(error, ex.getStatus());
	    }
	@ExceptionHandler
	 
    protected ResponseEntity<BankErrorResponse> handleMethodArgumentNotValid(
        MethodArgumentNotValidException ex) {
		BankErrorResponse error = new BankErrorResponse();
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setErrorMessage("Validation failed");
        error.setTimestamp(System.currentTimeMillis());
        
        List<BankErrorResponse.FieldError> fieldErrors = ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> new BankErrorResponse.FieldError(fieldError.getField(), fieldError.getDefaultMessage()))
                .collect(Collectors.toList());
        
        error.setFieldErrors(fieldErrors);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}
