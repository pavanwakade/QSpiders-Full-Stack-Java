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

    @PostMapping("/login")
    public ResponseEntity<Employee> login(@RequestParam String username, @RequestParam String password) {
        return service.login(username, password);
    }

    @PutMapping("/update")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {
        return service.updateEmployee(employee);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteEmployee(@RequestParam int id) {
        return service.deleteEmployee(id);
    }

    @GetMapping("/findById")
    public ResponseEntity<Employee> findById(@RequestParam int id) {
        return service.findById(id);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Employee>> findAll() {
        return service.findAll();
    }
}