package com.companywithemployee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.companywithemployee.Dao.EmployeeDao;
import com.companywithemployee.entity.Employee;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeDao dao;
	
	
	
	
	public Employee saveEmployee(Employee employee) {
		
		return dao.saveEmployee(employee);
	}
}
