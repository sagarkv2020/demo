package com.example.demo.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import com.example.demo.utils.RestResponseUtil;

@ControllerAdvice
public class GlobalExceptionHandler {

	private Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	public ResponseEntity<?> handleResourceNotFoundException(HttpServletRequest request, ResourceNotFoundException exception) {
		
		log.info("@ResourceNotFoundException : "+ exception.getMessage());
		
		return RestResponseUtil.badResponseEntity(exception.getMessage(), HttpStatus.NOT_FOUND);
	}
}
