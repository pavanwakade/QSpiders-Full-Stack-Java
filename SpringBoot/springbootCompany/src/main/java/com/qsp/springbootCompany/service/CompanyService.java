package com.qsp.springbootCompany.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.qsp.springbootCompany.dao.CompanyDao;
import com.qsp.springbootCompany.dto.Company;
import com.qsp.springbootCompany.exception.IdNotFoundException;
import com.qsp.springbootCompany.exception.LocationNotFoundException;
import com.qsp.springbootCompany.exception.NameNotFoundException;

@Service
public class CompanyService {

	@Autowired
	private CompanyDao dao;

	public ResponseEntity<List<Company>> FindByName(String name) {

		List<Company> companies = dao.findByName(name);

		if (!companies.isEmpty()) {
			
			return new ResponseEntity<List<Company>>(companies, HttpStatus.OK);
		}
		throw new NameNotFoundException();
	}
	
	
	

	public ResponseEntity<Company> saveCompany(Company company) {
		
		Company retcompany = dao.saveCompany(company);
		
		return new ResponseEntity<Company>(retcompany, HttpStatus.CREATED);
	}
	
	
	

	public ResponseEntity<Company> updateCompany(Company company) {

		Company company2 = dao.saveCompany(company);
		
		return new ResponseEntity<Company>(company2, HttpStatus.CREATED);
	}

	
	
	
	public ResponseEntity<Company> findById(int id) {

		Company company = null;
		
		Optional<Company> optional = dao.findCompanyById(id);

		if (optional.isPresent()) {

			company = optional.get();

			return new ResponseEntity<Company>(company, HttpStatus.OK);
		}

		throw new IdNotFoundException();
	}
	
	
	

	public ResponseEntity<List<Company>> findAll() {
		
		List<Company> company = dao.findAll();
		
		return new ResponseEntity<List<Company>>(company, HttpStatus.OK);
	}

	
	
	
	public ResponseEntity<List<Company>> findByLocation(String location) {
		
		List<Company> compineas = dao.findByLocation(location);
		
		if (!compineas.isEmpty()) {
			
			return new ResponseEntity<List<Company>>(compineas, HttpStatus.OK);
		}
		throw new LocationNotFoundException();
	}
	
	
	

	public ResponseEntity<String> deleteCompany(int id) {

		Optional<Company> optional = dao.findCompanyById(id);

		if (optional.isPresent()) {

			dao.deleteCompany(id);

			return new ResponseEntity<String>("company deleted", HttpStatus.OK);
		}

		throw new IdNotFoundException();
	}

}
