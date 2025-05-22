package com.qsp.springbootCompany.controller;

import com.qsp.springbootCompany.dto.Admin;
import com.qsp.springbootCompany.service.AdminService;
import com.qsp.springbootCompany.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private CompanyService companyService;

    @PostMapping("/register")
    @PreAuthorize("permitAll()")
    public ResponseEntity<Admin> register(@RequestBody Admin admin) {
        if (admin.getCompany() != null && companyService.findCompanyById(admin.getCompany().getId()).getBody().isApproved()) {
            return adminService.saveAdmin(admin);
        }
        throw new IllegalStateException("Company is not approved");
    }

    @GetMapping("/findById")
    @PreAuthorize("hasRole('COMPANY_ADMIN')")
    public ResponseEntity<Admin> findAdminById(@RequestParam int id) {
        return adminService.findAdminById(id);
    }

    @GetMapping("/findAll")
    @PreAuthorize("hasRole('COMPANY_ADMIN')")
    public ResponseEntity<List<Admin>> findAll() {
        return adminService.findAll();
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasRole('COMPANY_ADMIN')")
    public ResponseEntity<String> deleteAdmin(@RequestParam int id) {
        return adminService.deleteAdmin(id);
    }
}