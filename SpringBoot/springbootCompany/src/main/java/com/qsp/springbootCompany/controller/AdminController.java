package com.qsp.springbootCompany.controller;

import com.qsp.springbootCompany.dto.Admin;
import com.qsp.springbootCompany.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService service;

    @PostMapping("/register")
    public ResponseEntity<Admin> register(@RequestBody Admin admin) {
        return service.saveAdmin(admin);
    }

    @PostMapping("/login")
    public ResponseEntity<Admin> login(@RequestParam String username, @RequestParam String password) {
        return service.login(username, password);
    }

    @PutMapping("/update")
    public ResponseEntity<Admin> updateAdmin(@RequestBody Admin admin) {
        return service.updateAdmin(admin);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteAdmin(@RequestParam int id) {
        return service.deleteAdmin(id);
    }

    @GetMapping("/findById")
    public ResponseEntity<Admin> findById(@RequestParam int id) {
        return service.findById(id);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Admin>> findAll() {
        return service.findAll();
    }
}