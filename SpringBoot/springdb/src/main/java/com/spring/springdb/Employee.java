package com.spring.springdb;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

//@Entity
public class Employee {

//	@Id
	private int id;
	
	private String name;
	
	private double sal;
	
	
	

	public Employee() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getSal() {
		return sal;
	}

	public void setSal(double sal) {
		this.sal = sal;
	}
	
	
}
