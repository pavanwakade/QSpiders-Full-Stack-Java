package com.qsp.springbootCompany.dao;

import com.qsp.springbootCompany.dto.Admin;
import com.qsp.springbootCompany.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
public class AdminDao {

    @Autowired
    private AdminRepository repository;

    public Admin saveAdmin(Admin admin) {
        return repository.save(admin);
    }

    public Admin updateAdmin(Admin admin) {
        return repository.save(admin);
    }

    public void deleteAdmin(int id) {
        repository.deleteById(id);
    }

    public Optional<Admin> findAdminById(int id) {
        return repository.findById(id);
    }

    public Optional<Admin> findByUsername(String username) {
        return repository.findByUsername(username);
    }

    public Optional<Admin> findByUsernameAndPassword(String username, String password) { // Added to support login
        return repository.findByUsernameAndPassword(username, password);
    }

    public List<Admin> findAll() {
        return repository.findAll();
    }

    public List<Admin> findAdminsByCompanyId(int companyId) {
        return repository.findByCompany_Id(companyId);
    }
}