package com.sumit.controller;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.sumit.entity.ErrorResponse;
import com.sumit.exception.ProductNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	 @ExceptionHandler(ProductNotFoundException.class)
	    public ResponseEntity<?> handleProductNotFoundException(ProductNotFoundException exception){
	        ErrorResponse productNotFound = new ErrorResponse(LocalDateTime.now(), exception.getMessage(), "Product Not Found");
	        return new ResponseEntity<>(productNotFound, HttpStatus.NOT_FOUND);
	    }
}
