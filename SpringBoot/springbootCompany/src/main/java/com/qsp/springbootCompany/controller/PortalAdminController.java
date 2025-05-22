
package com.qsp.springbootCompany.controller;

import com.qsp.springbootCompany.dto.Company;
import com.qsp.springbootCompany.dto.PortalAdmin;
import com.qsp.springbootCompany.service.CompanyService;
import com.qsp.springbootCompany.service.PortalAdminService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/portal")
public class PortalAdminController {

    @Autowired
    private PortalAdminService portalAdminService;

    @Autowired
    private CompanyService companyService;

    @PostMapping("/register")
    public ResponseEntity<PortalAdmin> register(@RequestBody PortalAdmin portalAdmin) {
        return portalAdminService.savePortalAdmin(portalAdmin);
    }

    @PostMapping("/login")
    public ResponseEntity<PortalAdmin> login(@RequestBody PortalAdmin portalAdmin) {
        return portalAdminService.login(portalAdmin.getUsername(), portalAdmin.getPassword());
    }

    @PutMapping("/company/approve")
    public ResponseEntity<Company> approveCompany(@RequestParam int id) {
        return companyService.approveCompany(id);
    }

    @DeleteMapping("/company/delete")
    public ResponseEntity<String> deleteCompany(@RequestParam int id) {
        return companyService.deleteCompany(id);
    }

    @GetMapping("/company/employees")
    public ResponseEntity<List<CompanyEmployeesDTO>> getAllCompaniesWithEmployees() {
        // Implemented in CompanyController for simplicity
        return null; // Placeholder; actual logic in CompanyController
    }
}