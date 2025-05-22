package com.qsp.springbootCompany.controller;

import com.qsp.springbootCompany.dto.Task;
import com.qsp.springbootCompany.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/assign")
    @PreAuthorize("hasRole('COMPANY_ADMIN')")
    public ResponseEntity<Task> assignTask(@RequestBody Task task, @RequestParam int adminId) {
        return ResponseEntity.ok(taskService.assignTask(task, adminId));
    }

    @PutMapping("/updateStatus")
    @PreAuthorize("hasRole('COMPANY_ADMIN') or hasRole('EMPLOYEE')")
    public ResponseEntity<Task> updateTaskStatus(@RequestParam int id, @RequestParam String status, @RequestParam(required = false) String message) {
        return ResponseEntity.ok(taskService.updateTaskStatus(id, status, message));
    }
}