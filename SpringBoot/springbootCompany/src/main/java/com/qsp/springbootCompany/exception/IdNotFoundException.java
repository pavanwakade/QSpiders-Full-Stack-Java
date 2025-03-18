package com.qsp.springbootCompany.exception;

public class IdNotFoundException extends RuntimeException {

	String message="Company id not present";
	
	public IdNotFoundException() {
		// TODO Auto-generated constructor stub
	}

	public IdNotFoundException(String message) {
		super();
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "id not present";
	}
}
