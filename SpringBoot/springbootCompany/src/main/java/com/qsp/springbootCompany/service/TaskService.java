package com.qsp.springbootCompany.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qsp.springbootCompany.dto.Company;
import com.qsp.springbootCompany.dto.Task;
import com.qsp.springbootCompany.repository.TaskRepository;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private CompanyService companyService;

    @Transactional
    public Task assignTask(Task task, int adminId) {
        if (task.getEmployee() == null || task.getEmployee().getCompany() == null) {
            throw new IllegalArgumentException("Employee or company data missing");
        }
        Company company = companyService.findById(task.getEmployee().getCompany().getId());
        if (!company.isApproved()) {
            throw new IllegalStateException("Company is not approved");
        }
        task.setAssignedDate(LocalDate.now());
        task.setStatus("Pending");
        return taskRepository.save(task);
    }

    @Transactional
    public Task updateTaskStatus(int id, String status, String message) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Task not found"));
        if (!task.getEmployee().getCompany().isApproved()) {
            throw new IllegalStateException("Company is not approved");
        }
        task.setStatus(status);
        if (message != null) task.setMessage(message);
        return taskRepository.save(task);
    }
}