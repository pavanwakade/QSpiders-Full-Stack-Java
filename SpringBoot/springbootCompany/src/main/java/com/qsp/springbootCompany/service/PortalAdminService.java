package com.qsp.springbootCompany.service;

import com.qsp.springbootCompany.dao.PortalAdminDao;
import com.qsp.springbootCompany.dto.PortalAdmin;
import com.qsp.springbootCompany.exception.IdNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PortalAdminService {

    @Autowired
    private PortalAdminDao dao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ResponseEntity<PortalAdmin> savePortalAdmin(PortalAdmin portalAdmin) {
        portalAdmin.setPassword(passwordEncoder.encode(portalAdmin.getPassword()));
        PortalAdmin saved = dao.savePortalAdmin(portalAdmin);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }
}