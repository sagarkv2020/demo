package com.example.demo.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class RestResponseUtil {

	public static ResponseEntity<RestErrorResponse> badResponseEntity(String message, HttpStatus status) {

		RestErrorResponse response = new RestErrorResponse(status);
		response.put(message);

		return new ResponseEntity<RestErrorResponse>(response, status);
	}

}
