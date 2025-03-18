package com.qsp.springbootCompany.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    
    //without  ResponsetEntity Class
   
    
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
    public void deleteCompany(@RequestParam int id) {
       
        service.deleteCompany(id);
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
    
    
//    @GetMapping("/findbycustomsalary{salary}")
//    public List<Company>findByCustomSalary(@PathVariable int sal){
//		return service.FindBySalary(sal);
//    	
//    }
    
    
    
    
    //using  ResponsetEntity Class
    
    @PostMapping("/saveusingRequestEntity")
    public ResponseEntity<String> saveusingRequestEntity( Company company){
    
		return new ResponseEntity<String>("Company saved",HttpStatus.CREATED);
    	
    }
    
    @GetMapping("/getcompanybyid")
    
    public ResponseEntity<Company> getcompanyById(@RequestParam int id){
    
    	return service.findById(id);
    }
}
