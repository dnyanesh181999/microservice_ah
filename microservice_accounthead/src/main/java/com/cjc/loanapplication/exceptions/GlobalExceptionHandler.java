package com.cjc.loanapplication.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourseNotFoundException.class)
	public ResponseEntity<APIError> noEnquiryFoundException(ResourseNotFoundException e,HttpServletRequest request)
	{
		APIError error=new APIError();
		error.setDate(new Date());
		error.setStatuscode(HttpStatus.BAD_REQUEST.value());
		error.setMessage(e.getMessage());
		error.setPath(request.getRequestURI());
		error.setHttpmessage(HttpStatus.BAD_REQUEST);
		
		return new ResponseEntity<APIError>(error,HttpStatus.BAD_REQUEST);
	}
	

	
}
