package com.example.commentsapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;

public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleGeneralException(Exception e) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

//    @ExceptionHandler(UserNotFoundException.class)
//    public ResponseEntity<ErrorResponse>handleUserNotFoundException(Exception e) {
//        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND, e.getMessage());
//        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
//    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse>handleEntityNotFoundException(Exception e) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND, e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

//    @ExceptionHandler(UnauthorizedActionException.class)
//    public ResponseEntity<ErrorResponse>handleUnauthorizedActionException(Exception e) {
//        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND, e.getMessage());
//        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
//    }
}
