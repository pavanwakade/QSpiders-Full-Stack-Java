package com.companywithemployee.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

	public void deleteEmployee(int id) {

		Optional<Employee> optional = dao.findEmployeeById(id);

		if (optional.isPresent()) {
			optional.get();
		}
	}

	public ResponseEntity<Employee> findEmployeeById(int id) {
		Optional<Employee> optional = dao.findEmployeeById(id);
		Employee company = null;
		if (optional.isPresent()) {
			company = optional.get();
			return new ResponseEntity<Employee>(company, HttpStatus.OK);
		} else {
//            throw new IDNotExistException("Employee with ID " + id + " does not exist");
			return null;
		}
	}

	// Delete Company by ID
	public ResponseEntity<String> deleteCompanyById(int id) {
		Optional<Employee> optional = dao.findEmployeeById(id);
		if (optional.isPresent()) {
			dao.findEmployeeById(id);
			return new ResponseEntity<String>("Company is deleted", HttpStatus.OK);
		}
//        throw new IDNotExistException("Company with ID " + id + " does not exist");
		return null;
	}

	// Get all Companies
	public ResponseEntity<List<Employee>> findAllCompanies() {
		List<Employee> list = dao.findAllCompny();
		return new ResponseEntity<List<Employee>>(list, HttpStatus.OK);
	}
}
