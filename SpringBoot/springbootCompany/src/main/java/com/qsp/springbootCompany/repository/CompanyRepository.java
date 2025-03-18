package com.qsp.springbootCompany.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.qsp.springbootCompany.dto.Company;

public interface CompanyRepository extends JpaRepository<Company, Integer> {

	 List<Company> findByLocation(String location);
	 
	 
	 List<Company> findByName(String name);
	 
	
	 
//	 
//	@Query("select c from Company where c.salary=:sal")
//	public List<Company> companyFindBySalary(int sal);

	 
	 
}
