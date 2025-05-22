//package com.qsp.springbootCompany.dao;
//
//import com.qsp.springbootCompany.dto.Employee;
//import com.qsp.springbootCompany.repository.EmployeeRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//import java.util.Optional;
//
//@Repository
//public class EmployeeDao {
//
//    @Autowired
//    private EmployeeRepository repository;
//
//    public Employee saveEmployee(Employee employee) {
//        return repository.save(employee);
//    }
//
//    public Optional<Employee> findEmployeeById(int id) {
//        return repository.findById(id);
//    }
//
//    public List<Employee> findAll() {
//        return repository.findAll();
//    }
//
//    public Optional<Employee> findByUsername(String username) {
//        return repository.findByUsername(username);
//    }
//
//    public void deleteEmployee(int id) {
//        repository.deleteById(id);
//    }
//
//    public List<Employee> findEmployeesByCompanyId(int companyId) {
//        return repository.findByCompanyId(companyId);
//    }
//}