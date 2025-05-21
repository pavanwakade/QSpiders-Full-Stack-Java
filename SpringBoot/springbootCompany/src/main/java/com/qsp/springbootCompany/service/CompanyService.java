package com.qsp.springbootCompany.service;

import com.qsp.springbootCompany.dao.CompanyDao;
import com.qsp.springbootCompany.dto.Company;
import com.qsp.springbootCompany.exception.IdNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    @Autowired
    private CompanyDao dao;

    public ResponseEntity<Company> saveCompany(Company company) {
        Company savedCompany = dao.saveCompany(company);
        return new ResponseEntity<>(savedCompany, HttpStatus.CREATED);
    }

    public ResponseEntity<Company> updateCompany(Company company) {
        Company updatedCompany = dao.updateCompany(company);
        return new ResponseEntity<>(updatedCompany, HttpStatus.OK);
    }

    public ResponseEntity<Company> findById(int id) {
        Optional<Company> optional = dao.findCompanyById(id);
        if (optional.isPresent()) {
            return new ResponseEntity<>(optional.get(), HttpStatus.OK);
        }
        throw new IdNotFoundException("Company with ID " + id + " not found");
    }

    public ResponseEntity<String> deleteCompany(int id) {
        Optional<Company> optional = dao.findCompanyById(id);
        if (optional.isPresent()) {
            dao.deleteCompany(id);
            return new ResponseEntity<>("Company deleted", HttpStatus.OK);
        }
        throw new IdNotFoundException("Company with ID " + id + " not found");
    }

    public ResponseEntity<List<Company>> findAll() {
        List<Company> companies = dao.findAll();
        return new ResponseEntity<>(companies, HttpStatus.OK);
    }

    public ResponseEntity<List<Company>> findByLocation(String location) {
        List<Company> companies = dao.findByLocation(location);
        return new ResponseEntity<>(companies, HttpStatus.OK);
    }

    public ResponseEntity<List<Company>> findByName(String name) {
        List<Company> companies = dao.findByName(name);
        return new ResponseEntity<>(companies, HttpStatus.OK);
    }
}