package com.qsp.springbootCompany.controller;

import com.qsp.springbootCompany.dto.Company;
import com.qsp.springbootCompany.dto.PortalAdmin;
import com.qsp.springbootCompany.service.CompanyService;
import com.qsp.springbootCompany.service.PortalAdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/portal")
public class PortalAdminController {

    @Autowired
    private PortalAdminService portalAdminService;

    @Autowired
    private CompanyService companyService;

    @PostMapping("/register")
    @PreAuthorize("permitAll()")
    public ResponseEntity<PortalAdmin> register(@RequestBody PortalAdmin portalAdmin) {
        return portalAdminService.savePortalAdmin(portalAdmin);
    }

    @PutMapping("/company/approve")
    @PreAuthorize("hasRole('PORTAL_ADMIN')")
    public ResponseEntity<Company> approveCompany(@RequestParam int id) {
        return companyService.approveCompany(id);
    }

    @DeleteMapping("/company/delete")
    @PreAuthorize("hasRole('PORTAL_ADMIN')")
    public ResponseEntity<String> deleteCompany(@RequestParam int id) {
        return companyService.deleteCompany(id);
    }
}