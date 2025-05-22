package com.qsp.springbootCompany.controller;

import com.qsp.springbootCompany.dto.Employee;
import com.qsp.springbootCompany.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @PostMapping("/register")
    public ResponseEntity<Employee> register(@RequestBody Employee employee) {
        return service.saveEmployee(employee);
    }

    @GetMapping("/findById")
    public ResponseEntity<Employee> findEmployeeById(@RequestParam int id) {
        return service.findEmployeeById(id);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Employee>> findAll() {
        return service.findAll();
    }

    @PostMapping("/login")
    public ResponseEntity<Employee> login(@RequestBody Employee employee) {
        return service.login(employee.getUsername(), employee.getPassword());
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteEmployee(@RequestParam int id) {
        return service.deleteEmployee(id);
    }
}