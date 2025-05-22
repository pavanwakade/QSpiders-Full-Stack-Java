package com.qsp.springbootCompany.repository;

import com.qsp.springbootCompany.dto.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
    Optional<Admin> findByUsername(String username);
    List<Admin> findByCompany_Id(int companyId); // Added to find admins by company ID
}