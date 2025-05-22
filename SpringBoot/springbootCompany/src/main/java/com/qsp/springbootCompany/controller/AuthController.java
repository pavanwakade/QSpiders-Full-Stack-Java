package com.qsp.springbootCompany.controller;

import com.qsp.springbootCompany.config.JwtUtil;
import com.qsp.springbootCompany.dto.Admin;
import com.qsp.springbootCompany.dto.Employee;
import com.qsp.springbootCompany.dto.PortalAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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
    public ResponseEntity<?> portalLogin(@RequestBody PortalAdmin portalAdmin) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(portalAdmin.getUsername(), portalAdmin.getPassword()));
            final UserDetails userDetails = userDetailsService.loadUserByUsername(portalAdmin.getUsername());
            final String jwt = jwtUtil.generateToken(userDetails.getUsername());
            return ResponseEntity.ok(jwt);
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred during login");
        }
    }

    @PostMapping("/admin/login")
    public ResponseEntity<?> adminLogin(@RequestBody Admin admin) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(admin.getUsername(), admin.getPassword()));
            final UserDetails userDetails = userDetailsService.loadUserByUsername(admin.getUsername());
            final String jwt = jwtUtil.generateToken(userDetails.getUsername());
            return ResponseEntity.ok(jwt);
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred during login");
        }
    }

    @PostMapping("/employee/login")
    public ResponseEntity<?> employeeLogin(@RequestBody Employee employee) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(employee.getUsername(), employee.getPassword()));
            final UserDetails userDetails = userDetailsService.loadUserByUsername(employee.getUsername());
            final String jwt = jwtUtil.generateToken(userDetails.getUsername());
            return ResponseEntity.ok(jwt);
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred during login");
        }
    }

    @GetMapping("/auth/user")
    public ResponseEntity<?> getUserDetails(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            return ResponseEntity.ok(new UserResponse(authentication.getName(), authentication.getAuthorities().iterator().next().getAuthority()));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Not authenticated");
    }

    private static class UserResponse {
        private String username;
        private String role;

        public UserResponse(String username, String role) {
            this.username = username;
            this.role = role;
        }

        public String getUsername() {
            return username;
        }

        public String getRole() {
            return role;
        }
    }
}