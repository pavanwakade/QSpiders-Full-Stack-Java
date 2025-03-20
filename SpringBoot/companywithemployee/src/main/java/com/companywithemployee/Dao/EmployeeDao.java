package com.companywithemployee.Dao;

import java.util.List;
import java.util.Optional;

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
	
	public Optional<Employee> findEmployeeById(int id) {
		return repository.findById(id);
	}
	
	
	
	public void deleteEmployee(int id) {
		 repository.deleteById(id);
	}
	
	
	public List<Employee> findAllCompny() {
		return repository.findAll();
	}
}
