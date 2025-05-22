
package com.qsp.springbootCompany.controller;

import com.qsp.springbootCompany.dto.Task;
import com.qsp.springbootCompany.service.CompanyService;
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

    @Autowired
    private CompanyService companyService;

    @PostMapping("/assign")
    public ResponseEntity<Task> assignTask(@RequestBody Task task) {
        if (task.getEmployee() != null && task.getEmployee().getCompany() != null &&
                companyService.findCompanyById(task.getEmployee().getCompany().getId()).getBody().isApproved()) {
            return service.saveTask(task);
        }
        throw new IllegalStateException("Company is not approved");
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteTask(@RequestParam int id) {
        return service.deleteTask(id);
    }

    @GetMapping("/employeeTasks")
    public ResponseEntity<List<Task>> findTasksByEmployeeId(@RequestParam int employeeId) {
        return service.findTasksByEmployeeId(employeeId);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Task>> findAll() {
        return service.findAll();
    }

    @PutMapping("/updateStatus")
    public ResponseEntity<Task> updateTaskStatus(@RequestParam int id, @RequestParam String status, @RequestParam(required = false) String message) {
        Task task = service.findTasksByEmployeeId(service.findTaskById(id).getBody().getEmployee().getId()).getBody().get(0);
        if (task.getEmployee().getCompany() != null &&
                companyService.findCompanyById(task.getEmployee().getCompany().getId()).getBody().isApproved()) {
            return service.updateTaskStatus(id, status, message);
        }
        throw new IllegalStateException("Company is not approved");
    }

    @PutMapping("/updateMessage")
    public ResponseEntity<Task> updateTaskMessage(@RequestParam int id, @RequestParam String message) {
        Task task = service.findTasksByEmployeeId(service.findTaskById(id).getBody().getEmployee().getId()).getBody().get(0);
        if (task.getEmployee().getCompany() != null &&
                companyService.findCompanyById(task.getEmployee().getCompany().getId()).getBody().isApproved()) {
            return service.updateTaskMessage(id, message);
        }
        throw new IllegalStateException("Company is not approved");
    }
}