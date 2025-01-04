package com.sumit.exception;

public class ProductNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 7793947057651779264L;

	public ProductNotFoundException(String msg){
		super(msg);
	}
}
