package com.techlabs.bank.exception;

import org.springframework.http.HttpStatus;

public class CustomerNotFoundException extends BaseException {
    
    public CustomerNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
