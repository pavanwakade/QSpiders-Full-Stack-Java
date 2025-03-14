package com.qsp.springbootCompany.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.qsp.springbootCompany.dao.CompanyDao;
import com.qsp.springbootCompany.dto.Company;

@Service
public class CompanyService {

	@Autowired
	private CompanyDao dao;

	public Company saveCompany(Company company) {
		return dao.saveCompany(company);
	}

	public Company updateCompany(Company company) {
		return dao.updateCompany(company);
	}

	public void deleteCompany(Company company) {
		dao.updateCompany(company);
	}

	public Company CompanyfondById(int id) {
		Optional<Company> optional = dao.findCompanyById(id);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;

	}

}
