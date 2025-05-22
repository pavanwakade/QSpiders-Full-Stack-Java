package com.qsp.springbootCompany.controller;

import com.qsp.springbootCompany.dto.Employee;
import com.qsp.springbootCompany.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/register")
    @PreAuthorize("hasRole('COMPANY_ADMIN')")
    public ResponseEntity<Employee> register(@RequestBody Employee employee) {
        return ResponseEntity.ok(employeeService.saveEmployee(employee));
    }
}