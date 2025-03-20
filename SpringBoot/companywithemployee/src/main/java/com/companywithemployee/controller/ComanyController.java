package com.companywithemployee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.companywithemployee.entity.Company;
import com.companywithemployee.entity.Employee;
import com.companywithemployee.service.Companyservice;

@RestController
public class ComanyController {

	@Autowired
	private Companyservice service;

	
	@PostMapping("/saveCompany")
	public Company saveCompany(Company company) {
		return service.saveCompany(company);
	}

	
	@PostMapping("/updateCompany")
	public Company updateCompany(Company company) {
		return service.saveCompany(company);
	}

	
	@GetMapping("/findcompanybyid")
	public ResponseEntity<Company> findCompanyById(@RequestParam int id) {
		return service.findCompanyById(id);
	}

	
	@GetMapping("/findallcompanies")
	public ResponseEntity<List<Company>> getAllCompanies() {
		return service.findAllCompanies();
	}

	
	@DeleteMapping("/deletecompany/{id}")
	public ResponseEntity<Company> deleteEmployeeById(@PathVariable int id) {
		return service.findCompanyById(id);
	}
}
