package com.companywithemployee.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.companywithemployee.Dao.CompanyDao;
import com.companywithemployee.entity.Company;
import com.companywithemployee.entity.Employee;

@Service
public class Companyservice {

	@Autowired
	private CompanyDao dao;

	public Company saveCompany(Company company) {
		return dao.saveCompany(company);
	}

	
	public void deleteCompany(int id) {

		Optional<Company> optional = dao.findCompanyById(id);

		if (optional.isPresent()) {
			optional.get();
		}
	}
	
	public Company frindompanyById(int id){
		
		Optional<Company> optional = dao.findCompanyById(id);

		if (optional.isPresent()) {
		return	optional.get();
		}
		return null;
	}
}
