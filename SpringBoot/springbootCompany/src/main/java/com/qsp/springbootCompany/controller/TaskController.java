package com.qsp.springbootCompany.controller;

import com.qsp.springbootCompany.dto.Task;
import com.qsp.springbootCompany.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService service;

    @PostMapping("/assign")
    public ResponseEntity<Task> assignTask(@RequestBody Task task) {
        return service.saveTask(task);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteTask(@RequestParam int id) {
        return service.deleteTask(id);
    }

    @GetMapping("/employeeTasks")
    public ResponseEntity<List<Task>> findTasksByEmployeeId(@RequestParam int employeeId) {
        return service.findTasksByEmployeeId(employeeId);
    }

    @PutMapping("/updateStatus")
    public ResponseEntity<Task> updateTaskStatus(@RequestParam int id, @RequestParam String status) {
        return service.updateTaskStatus(id, status);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Task>> findAll() {
        return service.findAll();
    }
}