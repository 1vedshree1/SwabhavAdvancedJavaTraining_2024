package com.techlabs.company.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.techlabs.company.error.CompanyErrorResponse;


@ControllerAdvice
public class CompanyExceptionHandler {
	

	@ExceptionHandler
	 
    protected ResponseEntity<CompanyErrorResponse> handleMethodArgumentNotValid(
        MethodArgumentNotValidException ex) {
        CompanyErrorResponse error = new CompanyErrorResponse();
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setErrorMessage("Validation failed");
        error.setTimestamp(System.currentTimeMillis());
        
        List<CompanyErrorResponse.FieldError> fieldErrors = ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> new CompanyErrorResponse.FieldError(fieldError.getField(), fieldError.getDefaultMessage()))
                .collect(Collectors.toList());
        
        error.setFieldErrors(fieldErrors);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}


	


