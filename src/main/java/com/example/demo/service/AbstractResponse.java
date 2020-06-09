package com.example.demo.service;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public abstract class AbstractResponse {

    public <T> ResponseEntity<T> successResponse(T message) {
        return new ResponseEntity<>(message, new HttpHeaders(), HttpStatus.OK);
    }

    public <T> ResponseEntity<T> errorResponse(T message) {
        return new ResponseEntity<>(message, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
