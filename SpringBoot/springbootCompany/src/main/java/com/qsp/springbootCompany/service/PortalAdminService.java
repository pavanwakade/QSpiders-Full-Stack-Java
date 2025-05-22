package com.qsp.springbootCompany.service;

import com.qsp.springbootCompany.dao.PortalAdminDao;
import com.qsp.springbootCompany.dto.PortalAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PortalAdminService {

    @Autowired
    private PortalAdminDao dao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ResponseEntity<PortalAdmin> savePortalAdmin(PortalAdmin portalAdmin) {
        if (dao.findByUsername(portalAdmin.getUsername()).isPresent()) {
            throw new IllegalStateException("Username already exists");
        }
        portalAdmin.setPassword(passwordEncoder.encode(portalAdmin.getPassword()));
        portalAdmin.setRole("ROLE_PORTAL_ADMIN");
        PortalAdmin savedAdmin = dao.savePortalAdmin(portalAdmin);
        return new ResponseEntity<>(savedAdmin, HttpStatus.CREATED);
    }
}