package com.companywithemployee.Dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.companywithemployee.entity.Employee;
import com.companywithemployee.repository.EmployeeRepository;

@Component
public class EmployeeDao {

	
	@Autowired
	private EmployeeRepository repository;
	
	public Employee saveEmployee(Employee employee) {
		return repository.save(employee);
	}
}
