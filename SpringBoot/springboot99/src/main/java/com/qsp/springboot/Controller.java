package com.qsp.springboot;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

	@GetMapping("/hi")
	public String getStrins() {
		return "Hello from Spring";

	}

	@GetMapping("/getstudentinJSON")

	public Student getStudentinfoInJSON(@RequestBody Student student) {
		return student;

	}

	@GetMapping("/getEmpBySalary")

	public Employee getEmpBySalary(@RequestBody List<Employee> emplist) {

		
		for (Employee employee : emplist) {

			if (employee.getSalary() > 7) {
				return employee;
			}
		}
		return null;
	}
	
	
	@GetMapping("/update")
	public String updateEmp() {
		return "updated";
		
	}
	
//	@GetMapping("/update")
//	public String updateEmp() {
//		return "updated";
//		
//	}

}