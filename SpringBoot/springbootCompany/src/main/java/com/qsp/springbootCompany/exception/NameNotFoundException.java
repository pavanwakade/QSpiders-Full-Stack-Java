package com.qsp.springbootCompany.exception;

public class NameNotFoundException extends RuntimeException {

	String message="company name not found";
	
	
	

	public NameNotFoundException() {
		super();
	}




	public NameNotFoundException(String message) {
		super();
		this.message = message;
	}
	
	
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "company name not found";
	}
}
