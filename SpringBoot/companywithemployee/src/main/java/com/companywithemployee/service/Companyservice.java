package com.companywithemployee.service;

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
	
	public Employee deleteCompany(int id) {
		return dao.d
	}
}
