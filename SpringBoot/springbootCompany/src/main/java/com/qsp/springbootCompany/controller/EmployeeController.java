package com.qsp.springbootCompany.controller;

import com.qsp.springbootCompany.dto.Employee;
import com.qsp.springbootCompany.dto.EmployeeLoginDTO;
import com.qsp.springbootCompany.service.CompanyService;
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

    @Autowired
    private CompanyService companyService;

    @PostMapping("/register")
    public ResponseEntity<Employee> register(@RequestBody Employee employee) {
        if (employee.getCompany() != null && companyService.findCompanyById(employee.getCompany().getId()).getBody().isApproved()) {
            return service.saveEmployee(employee);
        }
        throw new IllegalStateException("Company is not approved");
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
    public ResponseEntity<EmployeeLoginDTO> login(@RequestBody Employee employee) {
        Employee emp = service.login(employee.getUsername(), employee.getPassword()).getBody();
        if (emp.getCompany() != null && companyService.findCompanyById(emp.getCompany().getId()).getBody().isApproved()) {
            EmployeeLoginDTO dto = new EmployeeLoginDTO(
                emp.getId(),
                emp.getUsername(),
                emp.getEmail(),
                emp.getCompany().getId()
            );
            return ResponseEntity.ok(dto);
        }
        throw new IllegalStateException("Company is not approved");
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteEmployee(@RequestParam int id) {
        return service.deleteEmployee(id);
    }
}