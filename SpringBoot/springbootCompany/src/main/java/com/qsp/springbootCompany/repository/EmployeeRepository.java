package com.qsp.springbootCompany.repository;

import com.qsp.springbootCompany.dto.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    @Query("SELECT e FROM Employee e WHERE e.username = ?1")
    Optional<Employee> findByUsername(String username);

    @Query("SELECT e FROM Employee e WHERE e.company.id = ?1")
    List<Employee> findByCompanyId(int companyId);
}