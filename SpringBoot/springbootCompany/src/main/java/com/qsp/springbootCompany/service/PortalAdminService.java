package com.qsp.springbootCompany.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qsp.springbootCompany.dto.PortalAdmin;
import com.qsp.springbootCompany.repository.PortalAdminRepository;

@Service
public class PortalAdminService {

    @Autowired
    private PortalAdminRepository portalAdminRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public PortalAdmin savePortalAdmin(PortalAdmin portalAdmin) {
        // Store password as plain text (NOT RECOMMENDED)
        // portalAdmin.setPassword(passwordEncoder.encode(portalAdmin.getPassword()));
        return portalAdminRepository.save(portalAdmin);
    }
}