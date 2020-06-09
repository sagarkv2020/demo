package com.example.demo.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class AbstractResponseTest {

    private AbstractResponse abstractResponseUnderTest;

    @Before
    public void setUp() {
        abstractResponseUnderTest = new AbstractResponse() {
        };
    }

    @Test
    public void testSuccessResponse() {
        // Setup
        final String message = "Success";
        final ResponseEntity<String> expected = new ResponseEntity<>(message, HttpStatus.OK);
        // Run the test
        final ResponseEntity<String> result = AbstractResponse.successResponse(message);

        // Verify the results
        assertEquals(result, expected);
    }

    @Test
    public void testErrorResponse() {
        // Setup
        final String message = "ERROR";
        final ResponseEntity<String> expected = new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
        // Run the test
        final ResponseEntity<String> result = AbstractResponse.errorResponse(message);

        // Verify the results
        assertEquals(result, expected);
    }
}
