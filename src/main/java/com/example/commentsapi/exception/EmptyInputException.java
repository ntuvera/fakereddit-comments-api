package com.example.commentsapi.exception;

import org.springframework.http.HttpStatus;

public class EmptyInputException extends Exception {
    public EmptyInputException(HttpStatus notFound, String message) {
        super(message);
    }
}
