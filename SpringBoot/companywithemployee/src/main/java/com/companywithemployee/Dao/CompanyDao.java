package com.companywithemployee.Dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.companywithemployee.entity.Company;
import com.companywithemployee.entity.Employee;
import com.companywithemployee.repository.CompanyRepository;

@Component
public class CompanyDao {

	@Autowired
	private CompanyRepository repository;

	
	
	
	public Company saveCompany(Company company) {
		return repository.save(company);
	}
	
	
	public Employee deleteCompany(int id) {
		return repository.deleteById(id);;
	}

}
