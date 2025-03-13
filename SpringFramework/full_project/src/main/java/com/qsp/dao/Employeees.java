package com.qsp.dao;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Employeees {

	@Id
	private int id;

	private String name;

	private long phone;

	private double salary;

	public Employeees() {
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

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

}
