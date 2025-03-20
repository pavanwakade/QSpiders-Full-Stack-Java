package com.companywithemployee.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.companywithemployee.Dao.CompanyDao;
import com.companywithemployee.entity.Company;

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
	
	public ResponseEntity<Company> findCompanyById(int id) {
        Optional<Company> optional = dao.findCompanyById(id);
        Company company = null;
        if (optional.isPresent()) {
            company = optional.get();
            return new ResponseEntity<Company>(company, HttpStatus.OK);
        } else {
//            throw new IDNotExistException("Company with ID " + id + " does not exist");
        	return null;
        }
    }
	
	
	// Delete Company by ID
    public ResponseEntity<String> deleteCompanyById(int id) {
        Optional<Company> optional = dao.findCompanyById(id);
        if (optional.isPresent()) {
            dao.findCompanyById(id);
            return new ResponseEntity<String>("Company is deleted", HttpStatus.OK);
        }
//        throw new IDNotExistException("Company with ID " + id + " does not exist");
        return null;
    }
    

    // Get all Companies
    public ResponseEntity<List<Company>> findAllCompanies() {
        List<Company> list = dao.findAllCompny();
        return new ResponseEntity<List<Company>>(list, HttpStatus.OK);
    }
    
    
    
    
	
	
}
