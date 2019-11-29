package com.example.commentsapi.exception;

import org.springframework.http.HttpStatus;

public class ErrorResponse {

    private HttpStatus httpStatus;
    private String message;
    private String cause;
    private String timestamp;

    public ErrorResponse(HttpStatus httpStatus, String message, String cause, String timestamp) {
        super();
        this.httpStatus = httpStatus;
        this.message = message;
        this.cause = cause;
        this.timestamp = timestamp;
    }

    public ErrorResponse(HttpStatus httpStatus, String message) {
        super();
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public ErrorResponse(String message) {
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
