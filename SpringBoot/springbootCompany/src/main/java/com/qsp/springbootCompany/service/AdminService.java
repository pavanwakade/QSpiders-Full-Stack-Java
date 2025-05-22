package com.qsp.springbootCompany.service;

import com.qsp.springbootCompany.dao.AdminDao;
import com.qsp.springbootCompany.dto.Admin;
import com.qsp.springbootCompany.exception.IdNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private AdminDao adminDao;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ResponseEntity<Admin> saveAdmin(Admin admin) {
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        Admin savedAdmin = adminDao.saveAdmin(admin);
        return new ResponseEntity<>(savedAdmin, HttpStatus.CREATED);
    }

    public ResponseEntity<Admin> findAdminById(int id) {
        Optional<Admin> optional = adminDao.findAdminById(id);
        if (optional.isPresent()) {
            return new ResponseEntity<>(optional.get(), HttpStatus.OK);
        }
        throw new IdNotFoundException();
    }

    public ResponseEntity<List<Admin>> findAll() {
        List<Admin> admins = adminDao.findAll();
        return new ResponseEntity<>(admins, HttpStatus.OK);
    }

    public ResponseEntity<Admin> findByUsername(String username) {
        Optional<Admin> optional = adminDao.findByUsername(username);
        if (optional.isPresent()) {
            return new ResponseEntity<>(optional.get(), HttpStatus.OK);
        }
        throw new IdNotFoundException("Admin with username " + username + " not found");
    }

    public ResponseEntity<String> deleteAdmin(int id) {
        Optional<Admin> optional = adminDao.findAdminById(id);
        if (optional.isPresent()) {
            adminDao.deleteAdmin(id);
            return new ResponseEntity<>("Admin deleted", HttpStatus.OK);
        }
        throw new IdNotFoundException();
    }
}