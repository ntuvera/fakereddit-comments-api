package com.example.commentsapi.exception;

import org.springframework.http.HttpStatus;

public class EmptyInputException extends Exception {
    private HttpStatus httpStatus;

    public EmptyInputException(HttpStatus httpStatus, String message) {
        super(message);
    }
}
