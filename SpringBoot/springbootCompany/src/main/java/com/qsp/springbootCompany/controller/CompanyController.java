package com.qsp.springbootCompany.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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

//    http://localhost:8080/company
    @PostMapping("/company")
    public Company saveCompany(@RequestBody Company company) {
        return service.saveCompany(company);
    }
    
    
//    http://localhost:8080/company
    @PostMapping("/companyupdate")
    public Company updateCompany(@RequestBody Company company) {
        return service.updateCompany(company);
    }
    
    
        //http://localhost:8080/company?id=1
    @DeleteMapping("/company")
    public String deleteCompany(@RequestParam int id) {
        Company company = service.CompanyfondById(id);
        if (company != null) {
            service.CompanyfondById(id);
            service.deleteCompany(company);
            return "Company deleted successfully";
        }
        return "Company not found";
    }
    
    
    //http://localhost:8080/findallcompany
    @GetMapping("/findallcompany")
    public List<Company> findAllCompanys() {
        return service.findAll();
    }
    
    
    //http://localhost:8080/findcompanybylocation?location=pune
    @GetMapping("/findcompanybylocation")
    public List<Company>findByLocation(@RequestParam String location){
    	return service.findByLocation(location);
    }
}
