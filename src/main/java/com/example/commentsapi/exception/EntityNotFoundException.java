package com.example.commentsapi.exception;

import org.springframework.http.HttpStatus;

public class EntityNotFoundException extends Exception {
    public EntityNotFoundException(HttpStatus notFound, String message) {
        super(message);
    }
}
