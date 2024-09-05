package com.techlabs.loan.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.techlabs.loan.error.LoanErrorResponse;



@ControllerAdvice
public class LoanExceptionHandler {
	
	@ExceptionHandler
	 
    protected ResponseEntity<LoanErrorResponse> handleMethodArgumentNotValid(
        MethodArgumentNotValidException ex) {
        LoanErrorResponse error = new LoanErrorResponse();
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setErrorMessage("Validation failed");
        error.setTimestamp(System.currentTimeMillis());
        
        List<LoanErrorResponse.FieldError> fieldErrors = ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> new LoanErrorResponse.FieldError(fieldError.getField(), fieldError.getDefaultMessage()))
                .collect(Collectors.toList());
        
        error.setFieldErrors(fieldErrors);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}
