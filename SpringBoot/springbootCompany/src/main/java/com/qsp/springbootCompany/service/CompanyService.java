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

	public void deleteCompany(int id) {

		Optional<Company> optional = dao.findCompanyById(id);

		if (optional.isPresent()) {
			dao.deleteCompany(id);
		}
		throw new IdNotFoundException();

	}

	public Company CompanyfondById(int id) {
		Optional<Company> optional = dao.findCompanyById(id);
		if (optional.isPresent()) {
			return optional.get();
		}
		throw new IdNotFoundException();

	}

	public List<Company> findAll() {
		return dao.findAll();
	}

	public List<Company> findByLocation(String location) {
		List<Company> compineas = dao.findByLocation(location);
		if (!compineas.isEmpty()) {
			return dao.findByLocation(location);
		}
		return null;
	}

//	public List<Company>FindBySalary(int sal){
//		
//		return dao.findBySalary(sal);
//		
//	}

	// using ResponsetEntity Class

	public ResponseEntity<Company> saveusingRequestEntity(Company company) {
		Company retcompany = dao.saveCompany(company);
		return new ResponseEntity<Company>(retcompany, HttpStatus.CREATED);

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

	public ResponseEntity<String> deleteCompany1(int id) {

		Optional<Company> optional = dao.findCompanyById(id);

		if (optional.isPresent()) {

			dao.deleteCompany(id);

			return new ResponseEntity<String>("company deleted", HttpStatus.OK);
		}
		return new ResponseEntity<String>("company not deleted", HttpStatus.NOT_FOUND);

	}

}
