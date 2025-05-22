package com.qsp.springbootCompany.service;

import com.qsp.springbootCompany.dao.EmployeeDao;
import com.qsp.springbootCompany.dto.Employee;
import com.qsp.springbootCompany.exception.IdNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeDao dao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ResponseEntity<Employee> saveEmployee(Employee employee) {
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        Employee savedEmployee = dao.saveEmployee(employee);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    public ResponseEntity<Employee> findEmployeeById(int id) {
        Optional<Employee> optional = dao.findEmployeeById(id);
        if (optional.isPresent()) {
            return new ResponseEntity<>(optional.get(), HttpStatus.OK);
        }
        throw new IdNotFoundException();
    }

    public ResponseEntity<List<Employee>> findAll() {
        List<Employee> employees = dao.findAll();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    public ResponseEntity<Employee> findByUsername(String username) {
        Optional<Employee> optional = dao.findByUsername(username);
        if (optional.isPresent()) {
            return new ResponseEntity<>(optional.get(), HttpStatus.OK);
        }
        throw new IdNotFoundException("Employee with username " + username + " not found");
    }

    public ResponseEntity<String> deleteEmployee(int id) {
        Optional<Employee> optional = dao.findEmployeeById(id);
        if (optional.isPresent()) {
            dao.deleteEmployee(id);
            return new ResponseEntity<>("Employee and associated tasks deleted", HttpStatus.OK);
        }
        throw new IdNotFoundException("Employee with ID " + id + " not found");
    }
}