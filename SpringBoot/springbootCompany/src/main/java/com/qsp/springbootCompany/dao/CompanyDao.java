package com.qsp.springbootCompany.dao;

import com.qsp.springbootCompany.dto.Company;
import com.qsp.springbootCompany.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
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
        company.setApproved(false); // Default to unapproved
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
            adminDao.findAdminsByCompanyId(id).forEach(admin -> adminDao.deleteAdmin(admin.getId()));
            // Delete associated employees (which deletes their tasks)
            employeeDao.findAll().stream()
                    .filter(emp -> emp.getCompany() != null && emp.getCompany().getId() == id)
                    .forEach(emp -> employeeDao.deleteEmployee(emp.getId()));
            // Delete company
            repository.deleteById(id);
        }
    }
}