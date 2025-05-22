package com.qsp.springbootCompany.repository;

import com.qsp.springbootCompany.dto.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
    Optional<Admin> findByUsername(String username);
    @Query("SELECT a FROM Admin a WHERE a.username = ?1 AND a.password = ?2")
    Optional<Admin> findByUsernameAndPassword(String username, String password);
    List<Admin> findByCompany_Id(int companyId);
}