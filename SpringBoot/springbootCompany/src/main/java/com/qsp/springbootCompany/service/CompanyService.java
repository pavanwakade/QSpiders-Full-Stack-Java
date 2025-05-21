package com.qsp.springbootCompany.service;

import com.qsp.springbootCompany.dao.CompanyDao;
import com.qsp.springbootCompany.dto.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

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
        Company company = dao.findById(id);
        if (company != null) {
            return new ResponseEntity<>(company, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public void deleteCompany(int id) {
        dao.deleteCompany(id);
    }

    public ResponseEntity<List<Company>> findAll() {
        List<Company> companies = dao.findAll();
        return new ResponseEntity<>(companies, HttpStatus.OK);
    }

    public ResponseEntity<List<Company>> findByLocation(String location) {
        List<Company> companies = dao.findByLocation(location);
        return new ResponseEntity<>(companies, HttpStatus.OK);
    }

    public ResponseEntity<List<Company>> FindByName(String name) {
        List<Company> companies = dao.FindByName(name);
        return new ResponseEntity<>(companies, HttpStatus.OK);
    }
}