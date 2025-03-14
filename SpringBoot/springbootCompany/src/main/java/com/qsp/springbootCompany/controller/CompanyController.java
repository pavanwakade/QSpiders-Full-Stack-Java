package com.qsp.springbootCompany.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.qsp.springbootCompany.dto.Company;
import com.qsp.springbootCompany.service.CompanyService;

@RestController
public class CompanyController {

    @Autowired
    private CompanyService service;

    @PostMapping("/company")
    public Company saveCompany(@RequestBody Company company) {
        return service.saveCompany(company);
    }
    
    
    @PostMapping("/companyupdate")
    public Company updateCompany(@RequestBody Company company) {
        return service.updateCompany(company);
    }
    
    
        
    @DeleteMapping("/company")
    public String deleteCompany(@RequestParam int id) {
        Company company = service.CompanyfondById(id);
        if (company != null) {
            service.CompanyfondById(id);
            return "Company deleted successfully";
        }
        return "Company not found";
    }
}
