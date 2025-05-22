package com.qsp.springbootCompany.repository;

import com.qsp.springbootCompany.dto.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Integer> {
    List<Task> findByEmployeeId(int employeeId);
    List<Task> findAll(); // Added to fetch all tasks
}