package com.qsp.springbootCompany.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import com.qsp.springbootCompany.dto.Company;
import com.qsp.springbootCompany.repository.CompanyRepository;

@Component
public class CompanyDao {

	@Autowired
	private CompanyRepository repository;

	public Company saveCompany(Company company) {
		return repository.save(company);
	}

	public Company updateCompany(Company company) {
		return repository.save(company);
	}

	public void deleteCompany(int id) {
		repository.deleteById(id);
	}

	public Optional<Company> findCompanyById(int id) {
		return repository.findById(id);
	}

	public List<Company> findAll() {
		return repository.findAll();
	}

	public List<Company> findByLocation(String location) {
		return repository.findByLocation(location);
	}

   public List<Company>findByName(String name){
	   return repository.findByName(name);
   }

	
	
}
