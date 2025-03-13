package com.qsp.springboot.Employee;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

	@Autowired
	EmployeeRepository repository;

	@PostMapping("/employee")
	public Employee EmployeeSave(@RequestBody Employee emp) {

		Employee e = repository.save(emp);
		return e;
	}

	@PutMapping("/employee")
	public Employee Employeeupdate(@RequestBody Employee emp) {

		Employee e = repository.save(emp);

		return e;
	}

	@DeleteMapping("/employee")
	public String EmployeeDeleteById(@RequestParam int id) {

		Optional<Employee> optional = repository.findById(id);

		if (optional.isPresent()) {

			Employee e = optional.get();

			repository.delete(e);

			return "Employee delete sucessfully with id: " + id;
		}
		return "Employee with id: " + id + " Not Found";
	}

	
//	http://localhost:8080/employee
	@GetMapping("employee")
	public List<Employee> findAllEmp() {

		List<Employee> list = repository.findAll();

		return list;
	}

//	http://localhost:8080/employee/101
	@GetMapping("/employee/{id}")
	public Employee findById(@PathVariable int id) {

		Optional<Employee> optional = repository.findById(id);

		if (optional.isPresent()) {

			Employee e = optional.get();

			return e;
		}

		return null;

	}
	
	
//	http://localhost:8080/employee/findbyname?name=kedar
	@GetMapping("employee/findbyname")
	public List<Employee> findEmployeeByName(@RequestParam String name){
		
		return repository.findByName(name);
	}
	
	
//	http://localhost:8080/employee/findbysal?salary=2
	@GetMapping("employee/findbysal")
	public List<Employee> findEmployeeBySal(@RequestParam double salary){
		
		return repository.findBySalary(salary);
	}
	
	
//	http://localhost:8080/employee/findbyNameAndSal?name=pavan&salary=22
	@GetMapping("employee/findbyNameAndSal")
	public List<Employee> findEmployeeByNameAndSal(@RequestParam String name, double salary){
		
		return repository.findByNameAndSalary(name,salary);
	}
	
	
//	http://localhost:8080/employee/findbyNameAndPhone?name=pavan&phone=987654321
	@GetMapping("employee/findbyNameAndPhone")
	public Employee findEmployeeByNameAndPhone(@RequestParam String name,long phone){
		
		return repository.findByNameAndPhone(name,phone);
	}
	
	
//	@GetMapping("employeefindbysal{sal}")
//	public List<Employee> employeefindbysal(@PathVariable double sal){
//		return repository.fetchBySalary(sal);
//	}

	

}
