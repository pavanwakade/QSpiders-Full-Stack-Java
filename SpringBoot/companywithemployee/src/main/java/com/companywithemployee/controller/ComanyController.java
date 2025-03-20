package com.companywithemployee.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.companywithemployee.entity.Company;
import com.companywithemployee.entity.Employee;
import com.companywithemployee.repository.CompanyRepository;
import com.companywithemployee.repository.EmployeeRepository;
import com.companywithemployee.service.Companyservice;

@RestController
public class ComanyController {

	@Autowired
	private Companyservice service;

	@Autowired
	private CompanyRepository companyrepository;
	
	
	@Autowired
	private EmployeeRepository emprepository;
	
	
	
	@PostMapping("/company")
	public ResponseEntity<Company> saveCompany(@RequestBody Company company) {
		
		return service.saveCompany(company);
	}
	
	
	
	@PatchMapping("/company/{companyid}/{employeeid}")
	public void mapcompanyToEmployee(@PathVariable int companyid,@PathVariable int employeeid) {
		
		Company company=companyrepository.findById(companyid).orElse(null);	
		if (company == null) {
			
			return;
		}
		
		Employee employee=emprepository.findById(employeeid).orElse(null);
		
		if (employee == null) {
			return;
		}
		
		employee.setCompany(company);
		
		emprepository.save(employee);
	}

	
	@PostMapping("/updateCompany")
	public ResponseEntity<Company> updateCompany(Company company) {
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
