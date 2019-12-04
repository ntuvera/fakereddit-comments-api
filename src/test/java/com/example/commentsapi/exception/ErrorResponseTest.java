package com.example.commentsapi.exception;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ErrorResponseTest {
    private ErrorResponse tempErrorResponse;

    @InjectMocks
    private ErrorResponse errorResponse;

    @Before
    public void initErrorResponse() {
        tempErrorResponse = new ErrorResponse();
        tempErrorResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
        tempErrorResponse.setMessage("bad message");
        tempErrorResponse.setCause("cause");
        tempErrorResponse.setTimestamp("tuesday");
    }

    @Test
    public void getHttpStatus() {
        assertEquals(HttpStatus.BAD_REQUEST, tempErrorResponse.getHttpStatus());
    }

    @Test
    public void setHttpStatus() {
        tempErrorResponse.setHttpStatus(HttpStatus.NOT_FOUND);
        assertEquals(HttpStatus.NOT_FOUND, tempErrorResponse.getHttpStatus());
    }

    @Test
    public void getMessage() {
        assertEquals("bad message", tempErrorResponse.getMessage());
    }

    @Test
    public void setMessage() {
        tempErrorResponse.setMessage("new bad message");
        assertEquals("new bad message", tempErrorResponse.getMessage());
    }

    @Test
    public void getCause() {
        assertEquals("cause", tempErrorResponse.getCause());
    }

    @Test
    public void setCause() {
        tempErrorResponse.setCause("new cause");
        assertEquals("new cause", tempErrorResponse.getCause());
    }

    @Test
    public void getTimestamp() {
        assertEquals("tuesday", tempErrorResponse.getTimestamp());
    }

    @Test
    public void setTimestamp() {
        tempErrorResponse.setTimestamp("monday");
        assertEquals("monday", tempErrorResponse.getTimestamp());
    }

}
