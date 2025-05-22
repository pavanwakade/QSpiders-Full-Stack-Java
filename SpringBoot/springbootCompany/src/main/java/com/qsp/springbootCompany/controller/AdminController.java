
package com.qsp.springbootCompany.controller;

import com.qsp.springbootCompany.dto.Admin;
import com.qsp.springbootCompany.service.AdminService;
import com.qsp.springbootCompany.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService service;

    @Autowired
    private CompanyService companyService;

    @PostMapping("/register")
    public ResponseEntity<Admin> register(@RequestBody Admin admin) {
        if (admin.getCompany() != null && companyService.findCompanyById(admin.getCompany().getId()).getBody().isApproved()) {
            return service.saveAdmin(admin);
        }
        throw new IllegalStateException("Company is not approved");
    }

    @PostMapping("/login")
    public ResponseEntity<Admin> login(@RequestBody Admin admin) {
        Admin adm = service.login(admin.getUsername(), admin.getPassword()).getBody();
        if (adm.getCompany() != null && companyService.findCompanyById(adm.getCompany().getId()).getBody().isApproved()) {
            return ResponseEntity.ok(adm);
        }
        throw new IllegalStateException("Company is not approved");
    }
}