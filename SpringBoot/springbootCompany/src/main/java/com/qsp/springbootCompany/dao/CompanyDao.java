package com.qsp.springbootCompany.dao;

import org.springframework.beans.factory.annotation.Autowired;
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
}
