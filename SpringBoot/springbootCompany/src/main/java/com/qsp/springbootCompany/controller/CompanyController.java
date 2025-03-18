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

	// with ResponsetEntity Class

	
	
	
//    http://localhost:8080/company
	@PostMapping("/company")
	public ResponseEntity<Company> saveCompany(@RequestBody Company company) {
		
		return service.saveCompany(company);
	}
	
	
	

//    http://localhost:8080/companyupdate
	@PostMapping("/companyupdate")
	public ResponseEntity<Company> updateCompany(@RequestBody Company company) {
		
		return service.updateCompany(company);
	}

	
	
	
//	http://localhost:8080/getcompanybyid?id=2
	@GetMapping("/getcompanybyid")
	public ResponseEntity<Company> getcompanyById(@RequestParam int id) {

		return service.findById(id);
	}

	
	
	
	// http://localhost:8080/company?id=1
	@DeleteMapping("/company")
	public void deleteCompany(@RequestParam int id) {

		service.deleteCompany(id);
	}

	
	
	
	// http://localhost:8080/findallcompany
	@GetMapping("/findallcompany")
	public ResponseEntity<List<Company>> findAllCompany() {
		
		return service.findAll();
	}

	
	
	
	// http://localhost:8080/findcompanybylocation?location=pune
	@GetMapping("/findcompanybylocation")
	public ResponseEntity<List<Company>> findByLocation(@RequestParam String location) {
		
		return service.findByLocation(location);
	}

	
	
	
//	http://localhost:8080/findbyName/infosys
	@GetMapping("/findbyName/{name}")
	public ResponseEntity<List<Company>> findBycompanyName(@PathVariable String name) {
		
		return service.FindByName(name);

	}

}
