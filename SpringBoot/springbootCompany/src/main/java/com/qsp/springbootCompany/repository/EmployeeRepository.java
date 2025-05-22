package com.qsp.springbootCompany.repository;

import com.qsp.springbootCompany.dto.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Optional<Employee> findByUsername(String username);

    @Query("SELECT e FROM Employee e WHERE e.username = ?1 AND e.password = ?2")
    Optional<Employee> findByUsernameAndPassword(String username, String password);
}