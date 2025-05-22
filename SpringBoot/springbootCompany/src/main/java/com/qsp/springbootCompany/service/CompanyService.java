package com.qsp.springbootCompany.service;

import com.qsp.springbootCompany.dto.Company;
import com.qsp.springbootCompany.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Transactional
    public Company saveCompany(Company company) {
        company.setApproved(false);
        return companyRepository.save(company);
    }

    public Company findById(int id) {
        return companyRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Company not found"));
    }

    @Transactional
    public Company approveCompany(int id) {
        Company company = findById(id);
        company.setApproved(true);
        return companyRepository.save(company);
    }

    @Transactional
    public void deleteCompany(int id) {
        companyRepository.deleteById(id);
    }
}