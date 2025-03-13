package com.qsp.springbootCompany.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qsp.springbootCompany.dto.Company;

public interface CompanyRepository extends JpaRepository<Company, Integer> {

}
