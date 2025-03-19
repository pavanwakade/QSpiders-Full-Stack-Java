package com.companywithemployee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.companywithemployee.entity.Company;

public interface CompanyRepository extends JpaRepository<Company, Integer> {

}
