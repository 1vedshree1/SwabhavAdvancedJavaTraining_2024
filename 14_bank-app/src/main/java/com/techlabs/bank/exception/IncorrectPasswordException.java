package com.techlabs.bank.exception;

import org.springframework.http.HttpStatus;

public class IncorrectPasswordException extends BaseException {

    public IncorrectPasswordException(String message) {
        super(message, HttpStatus.BAD_REQUEST); 
    }
}
