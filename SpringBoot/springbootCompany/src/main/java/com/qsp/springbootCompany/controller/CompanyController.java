package com.qsp.springbootCompany.controller;

import com.qsp.springbootCompany.dto.Company;
import com.qsp.springbootCompany.dto.Employee;
import com.qsp.springbootCompany.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyService service;

    @PostMapping
    public ResponseEntity<Company> saveCompany(@RequestBody Company company) {
        return service.saveCompany(company);
    }

    @GetMapping("/findById")
    public ResponseEntity<Company> findCompanyById(@RequestParam int id) {
        return service.findCompanyById(id);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Company>> findAll() {
        return service.findAll();
    }

    @GetMapping("/employees")
    public ResponseEntity<List<CompanyEmployeesDTO>> getAllCompaniesWithEmployees() {
        List<Company> companies = service.findAll().getBody();
        if (companies == null) {
            return new ResponseEntity<>(List.of(), HttpStatus.OK); // Return empty list if no companies
        }
        List<CompanyEmployeesDTO> result = companies.stream().map(company -> {
            List<Employee> employees = company.getEmployees();
            int employeeCount = employees != null ? employees.size() : 0;
            List<String> employeeNames = employees != null ?
                    employees.stream().map(Employee::getUsername).collect(Collectors.toList()) : List.of();
            return new CompanyEmployeesDTO(company.getId(), company.getName(), company.getLocation(),
                    company.isApproved(), employeeCount, employeeNames);
        }).collect(Collectors.toList());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}

class CompanyEmployeesDTO {
    private int id;
    private String name;
    private String location;
    private boolean approved;
    private int employeeCount;
    private List<String> employeeNames;

    public CompanyEmployeesDTO(int id, String name, String location, boolean approved, int employeeCount, List<String> employeeNames) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.approved = approved;
        this.employeeCount = employeeCount;
        this.employeeNames = employeeNames;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public boolean isApproved() {
        return approved;
    }

    public int getEmployeeCount() {
        return employeeCount;
    }

    public List<String> getEmployeeNames() {
        return employeeNames;
    }
}