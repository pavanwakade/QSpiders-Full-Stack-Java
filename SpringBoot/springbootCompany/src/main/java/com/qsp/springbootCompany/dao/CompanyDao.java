package com.qsp.springbootCompany.dao;

import com.qsp.springbootCompany.dto.Admin;
import com.qsp.springbootCompany.dto.Company;
import com.qsp.springbootCompany.dto.Employee;
import com.qsp.springbootCompany.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
public class CompanyDao {

    @Autowired
    public CompanyRepository repository;

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private AdminDao adminDao;

    @Autowired
    private TaskDao taskDao;

    public Company saveCompany(Company company) {
        company.setApproved(false);
        return repository.save(company);
    }

    public Optional<Company> findCompanyById(int id) {
        return repository.findById(id);
    }

    public Company approveCompany(int id) {
        Optional<Company> optional = repository.findById(id);
        if (optional.isPresent()) {
            Company company = optional.get();
            company.setApproved(true);
            return repository.save(company);
        }
        return null;
    }

    public void deleteCompany(int id) {
        Optional<Company> optional = repository.findById(id);
        if (optional.isPresent()) {
            // Delete associated admins
            List<Admin> admins = adminDao.findAdminsByCompanyId(id);
            admins.forEach(admin -> adminDao.deleteAdmin(admin.getId()));
            // Delete associated employees (which deletes their tasks)
            List<Employee> employees = employeeDao.findEmployeesByCompanyId(id);
            employees.forEach(emp -> employeeDao.deleteEmployee(emp.getId()));
            // Delete company
            repository.deleteById(id);
        }
    }
}