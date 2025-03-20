package com.companywithemployee.Dao;

import java.util.List;
import java.util.Optional;

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
	
	public Optional<Company> findCompanyById(int id) {
		return repository.findById(id);
	}
	
	
	
	public void deleteCompany(int id) {
		 repository.deleteCompanyById(id);
	}
	
	
	public List<Company> findAllCompny() {
		return repository.findAll();
	}

}
