package com.qsp.springbootCompany.controller;

import com.qsp.springbootCompany.config.JwtUtil;
import com.qsp.springbootCompany.dto.Admin;
import com.qsp.springbootCompany.dto.Employee;
import com.qsp.springbootCompany.dto.PortalAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/portal/login")
    public ResponseEntity<String> portalLogin(@RequestBody PortalAdmin portalAdmin) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(portalAdmin.getUsername(), portalAdmin.getPassword()));
        final UserDetails userDetails = userDetailsService.loadUserByUsername(portalAdmin.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails.getUsername());
        return ResponseEntity.ok(jwt);
    }

    @PostMapping("/admin/login")
    public ResponseEntity<String> adminLogin(@RequestBody Admin admin) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(admin.getUsername(), admin.getPassword()));
        final UserDetails userDetails = userDetailsService.loadUserByUsername(admin.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails.getUsername());
        return ResponseEntity.ok(jwt);
    }

    @PostMapping("/employee/login")
    public ResponseEntity<String> employeeLogin(@RequestBody Employee employee) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(employee.getUsername(), employee.getPassword()));
        final UserDetails userDetails = userDetailsService.loadUserByUsername(employee.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails.getUsername());
        return ResponseEntity.ok(jwt);
    }
}