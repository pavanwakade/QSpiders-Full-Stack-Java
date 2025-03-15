package com.qsp.springbootCompany.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.qsp.springbootCompany.dto.Company;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
//
//@Query("select c from Company where c.location='pune'")
//public List<Company> companyFindByLocation();

	 List<Company> findByLocation(String location);
}
