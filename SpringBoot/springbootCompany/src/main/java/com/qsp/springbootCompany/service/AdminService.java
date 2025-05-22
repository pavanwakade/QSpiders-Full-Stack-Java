package com.qsp.springbootCompany.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qsp.springbootCompany.dto.Admin;
import com.qsp.springbootCompany.dto.Company;
import com.qsp.springbootCompany.repository.AdminRepository;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private CompanyService companyService;

    @Transactional
    public Admin saveAdmin(Admin admin) {
        Company company = companyService.findById(admin.getCompany().getId());
        if (!company.isApproved()) {
            throw new IllegalStateException("Company is not approved");
        }
        return adminRepository.save(admin);
    }
}