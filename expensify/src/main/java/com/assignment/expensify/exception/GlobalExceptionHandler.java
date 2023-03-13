package com.assignment.expensify.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.assignment.expensify.dto.ResponseDetail;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public ResponseDetail mapException(Exception ex) {
		
		return new ResponseDetail(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.toString());
	}

}
