package com.qsp.springbootCompany.repository;

import com.qsp.springbootCompany.dto.PortalAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PortalAdminRepository extends JpaRepository<PortalAdmin, Integer> {
    @Query("SELECT p FROM PortalAdmin p WHERE p.username = ?1")
    Optional<PortalAdmin> findByUsername(String username);

    @Query("SELECT p FROM PortalAdmin p WHERE p.username = ?1 AND p.password = ?2")
    Optional<PortalAdmin> findByUsernameAndPassword(String username, String password);
}