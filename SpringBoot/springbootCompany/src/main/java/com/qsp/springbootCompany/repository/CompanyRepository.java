package com.qsp.springbootCompany.repository;

import com.qsp.springbootCompany.dto.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
}