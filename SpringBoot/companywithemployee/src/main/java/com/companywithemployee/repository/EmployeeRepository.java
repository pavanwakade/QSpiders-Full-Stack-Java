package com.companywithemployee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.companywithemployee.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

}
