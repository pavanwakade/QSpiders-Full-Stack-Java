package com.qsp.springbootCompany.service;

import com.qsp.springbootCompany.dao.AdminDao;
import com.qsp.springbootCompany.dto.Admin;
import com.qsp.springbootCompany.exception.IdNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private AdminDao dao;

    public ResponseEntity<Admin> saveAdmin(Admin admin) {
        Admin savedAdmin = dao.saveAdmin(admin);
        return new ResponseEntity<>(savedAdmin, HttpStatus.CREATED);
    }

    public ResponseEntity<Admin> updateAdmin(Admin admin) {
        Admin updatedAdmin = dao.updateAdmin(admin);
        return new ResponseEntity<>(updatedAdmin, HttpStatus.OK);
    }

    public ResponseEntity<String> deleteAdmin(int id) {
        Optional<Admin> optional = dao.findAdminById(id);
        if (optional.isPresent()) {
            dao.deleteAdmin(id);
            return new ResponseEntity<>("Admin deleted", HttpStatus.OK);
        }
        throw new IdNotFoundException();
    }

    public ResponseEntity<Admin> findById(int id) {
        Optional<Admin> optional = dao.findAdminById(id);
        if (optional.isPresent()) {
            return new ResponseEntity<>(optional.get(), HttpStatus.OK);
        }
        throw new IdNotFoundException();
    }

    public ResponseEntity<List<Admin>> findAll() {
        List<Admin> admins = dao.findAll();
        return new ResponseEntity<>(admins, HttpStatus.OK);
    }

    public ResponseEntity<Admin> login(String username, String password) {
        Optional<Admin> optional = dao.findByUsername(username);
        if (optional.isPresent()) {
            Admin admin = optional.get();
            if (admin.getPassword().equals(password)) {
                return new ResponseEntity<>(admin, HttpStatus.OK);
            }
        }
        throw new IdNotFoundException("Invalid credentials");
    }
}