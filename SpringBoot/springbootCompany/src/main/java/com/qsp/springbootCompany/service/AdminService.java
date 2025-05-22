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
    private AdminDao adminDao;

    @Autowired
    private CompanyService companyService;

    public ResponseEntity<Admin> saveAdmin(Admin admin) {
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

    public ResponseEntity<Admin> login(String username, String password) {
        Optional<Admin> optional = adminDao.findByUsernameAndPassword(username, password);
        if (optional.isPresent()) {
            Admin admin = optional.get();
            if (companyService.findCompanyById(admin.getCompany().getId()).getBody().isApproved()) {
                return new ResponseEntity<>(admin, HttpStatus.OK);
            }
            throw new IllegalStateException("Company is not approved");
        }
        throw new IdNotFoundException("Invalid username or password");
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