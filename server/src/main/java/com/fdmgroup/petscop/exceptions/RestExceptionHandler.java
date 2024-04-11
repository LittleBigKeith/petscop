package com.fdmgroup.petscop.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {
	
	/* Have to use these next lines of code for every exception you create */
	
	// Another example - conflict exception
	// Posting something twice will cause this error to be thrown
	@ExceptionHandler(ConflictException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	public ApiErrorResponse handle(ConflictException e) {
		return new ApiErrorResponse(e.getMessage());
	}

	@ExceptionHandler(UnprocessableEntityException.class)
	@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
	public ApiErrorResponse handle(UnprocessableEntityException e) {
		return new ApiErrorResponse(e.getMessage());
	}
	
}

