package com.qsp.springbootCompany.exception;

public class LocationNotFoundException extends RuntimeException{

	String message="Location not found";

	public LocationNotFoundException() {
		super();
	}

	public LocationNotFoundException(String message) {
		super();
		this.message = message;
	}
	
	
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "Location not found";
	}
}
