package com.example.commentsapi.exception;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class GlobalExceptionHandlerTest {
    private Exception temp1;
    private Exception temp2;
    @InjectMocks
    private GlobalExceptionHandler globalExceptionHandler;

    @InjectMocks
    private ErrorResponse tempResponse;

    @Before
    public void initExceptions() {
        temp1 = new Exception("test message");
        temp2 = new Exception("test message");
    }

    @Test
    public void handleGeneralException() {

//        ReponseEntity<> returnedResponse = globalExceptionHandler.handleGeneralException(temp1);

        tempResponse.setHttpStatus(HttpStatus.UNAUTHORIZED);
        tempResponse.setMessage("bad message");

        ResponseEntity<ErrorResponse> actual = globalExceptionHandler.handleGeneralException(new EmptyInputException(HttpStatus.BAD_REQUEST, "bad message"));

        assertEquals(actual.getBody().getMessage(), tempResponse.getMessage());
    }

}
