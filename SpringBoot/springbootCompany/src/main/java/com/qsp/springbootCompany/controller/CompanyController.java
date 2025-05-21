package com.qsp.springbootCompany.controller;

import com.qsp.springbootCompany.dto.Company;
import com.qsp.springbootCompany.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyService service;

    @PostMapping
    public ResponseEntity<Company> saveCompany(@RequestBody Company company) {
        return service.saveCompany(company);
    }

    @PutMapping
    public ResponseEntity<Company> updateCompany(@RequestBody Company company) {
        return service.updateCompany(company);
    }

    @GetMapping("/findById")
    public ResponseEntity<Company> getCompanyById(@RequestParam int id) {
        return service.findById(id);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteCompany(@RequestParam int id) {
        return service.deleteCompany(id);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Company>> findAllCompany() {
        return service.findAll();
    }

    @GetMapping("/findByLocation")
    public ResponseEntity<List<Company>> findByLocation(@RequestParam String location) {
        return service.findByLocation(location);
    }

    @GetMapping("/findByName/{name}")
    public ResponseEntity<List<Company>> findByName(@PathVariable String name) {
        return service.findByName(name);
    }
}