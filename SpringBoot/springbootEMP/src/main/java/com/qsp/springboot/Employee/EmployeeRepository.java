package com.qsp.springboot.Employee;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

	
	List<Employee> findByName(String name);
	
	List<Employee> findBySalary(double sal);
	
	List<Employee> findByNameAndSalary(String name,double sal);
	
	Employee findByNameAndPhone(String name,long phone);
	
//	@Query("select e from Employee where e.salary>sal")
//	List<Employee>fetchBySalary(double sal);
	

}
