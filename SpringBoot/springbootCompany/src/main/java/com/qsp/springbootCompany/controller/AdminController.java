package com.qsp.springbootCompany.controller;

import com.qsp.springbootCompany.dto.Admin;
import com.qsp.springbootCompany.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/register")
    @PreAuthorize("hasRole('PORTAL_ADMIN') or hasRole('COMPANY_ADMIN')")
    public ResponseEntity<Admin> register(@RequestBody Admin admin) {
        return ResponseEntity.ok(adminService.saveAdmin(admin));
    }
}