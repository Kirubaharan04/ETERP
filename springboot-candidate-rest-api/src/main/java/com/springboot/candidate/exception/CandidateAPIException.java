package com.springboot.candidate.exception;

import org.springframework.http.HttpStatus;

public class CandidateAPIException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private HttpStatus status;
	
	private String message;

	public CandidateAPIException(HttpStatus status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

	public CandidateAPIException(String message , HttpStatus status, String message1) {
		super(message);
		this.status = status;
		this.message = message1;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}
	
	 
	
}
