package com.qsp.springbootCompany.dao;

import com.qsp.springbootCompany.dto.PortalAdmin;
import com.qsp.springbootCompany.repository.PortalAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
public class PortalAdminDao {

    @Autowired
    private PortalAdminRepository repository;

    public PortalAdmin savePortalAdmin(PortalAdmin portalAdmin) {
        return repository.save(portalAdmin);
    }

    public Optional<PortalAdmin> findByUsernameAndPassword(String username, String password) {
        return repository.findByUsernameAndPassword(username, password);
    }
}