package com.qsp.springbootCompany.service;

import com.qsp.springbootCompany.dao.EmployeeDao;
import com.qsp.springbootCompany.dto.Employee;
import com.qsp.springbootCompany.exception.IdNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeDao dao;

    public ResponseEntity<Employee> saveEmployee(Employee employee) {
        Employee savedEmployee = dao.saveEmployee(employee);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    public ResponseEntity<Employee> updateEmployee(Employee employee) {
        Employee updatedEmployee = dao.updateEmployee(employee);
        return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
    }

    public ResponseEntity<String> deleteEmployee(int id) {
        Optional<Employee> optional = dao.findEmployeeById(id);
        if (optional.isPresent()) {
            dao.deleteEmployee(id);
            return new ResponseEntity<>("Employee deleted", HttpStatus.OK);
        }
        throw new IdNotFoundException();
    }

    public ResponseEntity<Employee> findById(int id) {
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

    public ResponseEntity<Employee> login(String username, String password) {
        Optional<Employee> optional = dao.findByUsername(username);
        if (optional.isPresent()) {
            Employee employee = optional.get();
            if (employee.getPassword().equals(password)) {
                return new ResponseEntity<>(employee, HttpStatus.OK);
            }
        }
        throw new IdNotFoundException("Invalid credentials");
    }
}