package com.rest.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NoRequestBodyException extends RuntimeException{
	
	public NoRequestBodyException() {
		// TODO Auto-generated constructor stub
		super();
	}
}
