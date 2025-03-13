package com.spring.springdb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

//	@Autowired
//	EmployeeRepository repository;
//	@PostMapping("/Employee")
//	public Employee saveEmployee(@RequestBody Employee emp) {
//		Employee e = repository.save(emp);
//		return e;
//
//	}
	
	@GetMapping("/hi")
	public String getStrins() {
		return "Hello from Spring";

	}

}
