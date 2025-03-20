package com.companywithemployee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.companywithemployee.entity.Employee;
import com.companywithemployee.service.EmployeeService;


@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService service;
	
	@PostMapping("/saveEmployee")
	public Employee saveEmployee(@RequestBody Employee employee) {
		return service.saveEmployee(employee);
	}
	
	
	
	@PostMapping("/updateEmployee")
	public Employee updateEmployee(Employee Employee) {
		return service.saveEmployee(Employee);
	}

	
	@GetMapping("/findEmployeebyid")
	public ResponseEntity<Employee> findEmployeeById(@RequestParam int id) {
		return service.findEmployeeById(id);
	}

	
	@GetMapping("/findallEmployee")
	public ResponseEntity<List<Employee>> getAllCompanies() {
		return service.findAllCompanies();
	}

	
	@DeleteMapping("/deleteemployee/{id}")
	public ResponseEntity<Employee> deleteEmployeeById(@PathVariable int id) {
		return service.findEmployeeById(id);
	}
}
