package com.qsp.springbootCompany.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
}
