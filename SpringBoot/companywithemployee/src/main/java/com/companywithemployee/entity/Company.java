package com.companywithemployee.entity;

import java.util.List;

import com.companywithemployee.util.Location;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Company {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	private Location location;
	
	@JsonIgnore
	@OneToMany(mappedBy = "company")
	List<Employee> employee;
	
	

	public Company() {
		super();
	}

	public Company(int id, String name, Location location, List<Employee> employee) {
		super();
		this.id = id;
		this.name = name;
		this.location = location;
		this.employee = employee;
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

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public List<Employee> getEmployee() {
		return employee;
	}

	public void setEmployee(List<Employee> employee) {
		this.employee = employee;
	}
	
	
	
	
	

}
