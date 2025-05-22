package com.qsp.springbootCompany.controller;

import com.qsp.springbootCompany.dto.Employee;
import com.qsp.springbootCompany.service.CompanyService;
import com.qsp.springbootCompany.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @Autowired
    private CompanyService companyService;

    @PostMapping("/register")
    @PreAuthorize("permitAll()")
    public ResponseEntity<Employee> register(@RequestBody Employee employee) {
        if (employee.getCompany() != null && companyService.findCompanyById(employee.getCompany().getId()).getBody().isApproved()) {
            return service.saveEmployee(employee);
        }
        throw new IllegalStateException("Company is not approved");
    }

    @GetMapping("/findById")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public ResponseEntity<Employee> findEmployeeById(@RequestParam int id) {
        return service.findEmployeeById(id);
    }

    @GetMapping("/findAll")
    @PreAuthorize("hasRole('COMPANY_ADMIN')")
    public ResponseEntity<List<Employee>> findAll() {
        return service.findAll();
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasRole('COMPANY_ADMIN')")
    public ResponseEntity<String> deleteEmployee(@RequestParam int id) {
        return service.deleteEmployee(id);
    }
}