package com.qsp.springbootCompany.repository;

import com.qsp.springbootCompany.dto.PortalAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PortalAdminRepository extends JpaRepository<PortalAdmin, Integer> {
    Optional<PortalAdmin> findByUsername(String username);
}