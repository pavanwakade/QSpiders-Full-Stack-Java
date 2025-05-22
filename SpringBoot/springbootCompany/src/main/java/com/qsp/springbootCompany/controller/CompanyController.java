package com.qsp.springbootCompany.controller;

import com.qsp.springbootCompany.dto.Company;
import com.qsp.springbootCompany.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @PostMapping
    public ResponseEntity<Company> saveCompany(@RequestBody Company company) {
        return ResponseEntity.ok(companyService.saveCompany(company));
    }
}