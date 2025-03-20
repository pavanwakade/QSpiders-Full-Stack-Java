package com.companywithemployee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.companywithemployee.entity.Company;
import com.companywithemployee.service.Companyservice;

@RestController
public class ComanyController {

	
	@Autowired
	private Companyservice service;
	
	
	@PostMapping("saveCompany")
	public Company saveCompany(Company company) {
		return service.saveCompany(company);
	}
	
	
	@PostMapping("/updateCompany")
	public Company updateCompany(Company company) {
		return service.saveCompany(company);
	}
	
	
	@GetMapping("/findcompanybyid")
	public Company findCompanyById(@RequestParam int id) {
		return service.frindompanyById(id);
	}
}
