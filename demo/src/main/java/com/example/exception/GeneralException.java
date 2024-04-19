package com.example.exception;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class GeneralException extends RuntimeException {

	private final String message;
	private final HttpStatus status;

	public GeneralException(String message, HttpStatus status) {
		this.message = message;
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public HttpStatus getStatus() {
		return status;
	}

}
