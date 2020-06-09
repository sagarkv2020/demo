package com.example.demo.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;

public class RestErrorResponse {

	private long timestamp;

	private HttpStatus status;

	private String statusText;

	private List<String> messages;

	/**
	 * @param badRequest
	 */
	public RestErrorResponse(HttpStatus badRequest) {
		this.status = badRequest;
		this.statusText = badRequest.toString();
		this.timestamp = System.currentTimeMillis();
		this.messages = new ArrayList<String>();
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getStatusText() {
		return statusText;
	}

	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}

	public List<String> getMessages() {
		return messages;
	}

	public void setMessages(List<String> messages) {
		this.messages = messages;
	}
	
	public void put(String message) {
		this.messages.add(message);
	}

}
