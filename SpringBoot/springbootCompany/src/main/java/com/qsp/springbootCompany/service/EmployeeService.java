package com.qsp.springbootCompany.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qsp.springbootCompany.dto.Company;
import com.qsp.springbootCompany.dto.Employee;
import com.qsp.springbootCompany.repository.EmployeeRepository;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CompanyService companyService;

    @Transactional
    public Employee saveEmployee(Employee employee) {
        Company company = companyService.findById(employee.getCompany().getId());
        if (!company.isApproved()) {
            throw new IllegalStateException("Company is not approved");
        }
        // Store password as plain text (NOT RECOMMENDED)
        // employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        return employeeRepository.save(employee);
    }
}