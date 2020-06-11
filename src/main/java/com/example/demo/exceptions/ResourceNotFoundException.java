package com.example.demo.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResourceNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("unused")
	private Logger log = LoggerFactory.getLogger(ResourceNotFoundException.class);
	
	public ResourceNotFoundException() {
		super();
	}
	
	public ResourceNotFoundException(final String exceptionMessage) {
		super(exceptionMessage);
	}
	
}
