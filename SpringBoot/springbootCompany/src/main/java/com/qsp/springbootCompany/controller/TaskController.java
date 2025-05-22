package com.qsp.springbootCompany.controller;

import com.qsp.springbootCompany.dto.Admin;
import com.qsp.springbootCompany.dto.Employee;
import com.qsp.springbootCompany.dto.Task;
import com.qsp.springbootCompany.service.CompanyService;
import com.qsp.springbootCompany.service.EmployeeService;
import com.qsp.springbootCompany.service.TaskService;
import com.qsp.springbootCompany.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService service;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private AdminService adminService;

    @PostMapping("/assign")
    @PreAuthorize("hasRole('COMPANY_ADMIN')")
    public ResponseEntity<Task> assignTask(@RequestBody Task task, @AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        Admin admin = adminService.findByUsername(username).getBody();
        if (admin != null && task.getEmployee() != null && task.getEmployee().getId() != null) {
            Employee employee = employeeService.findEmployeeById(task.getEmployee().getId()).getBody();
            if (employee != null && employee.getCompany().getId() == admin.getCompany().getId() && admin.getCompany().isApproved()) {
                task.setAssignedBy(admin);
                task.setEmployee(employee);
                return service.saveTask(task);
            }
            throw new IllegalStateException("Cannot assign task: invalid employee or company not approved");
        }
        throw new IllegalArgumentException("Invalid task data or admin");
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasRole('COMPANY_ADMIN')")
    public ResponseEntity<String> deleteTask(@RequestParam int id) {
        return service.deleteTask(id);
    }

    @GetMapping("/employeeTasks")
    @PreAuthorize("hasAnyRole('COMPANY_ADMIN', 'EMPLOYEE')")
    public ResponseEntity<List<Task>> findTasksByEmployeeId(@RequestParam int employeeId) {
        return service.findTasksByEmployeeId(employeeId);
    }

    @GetMapping("/findAll")
    @PreAuthorize("hasRole('COMPANY_ADMIN')")
    public ResponseEntity<List<Task>> findAll() {
        return service.findAll();
    }

    @PutMapping("/updateStatus")
    @PreAuthorize("hasAnyRole('COMPANY_ADMIN', 'EMPLOYEE')")
    public ResponseEntity<Task> updateTaskStatus(@RequestParam int id, @RequestParam String status,
                                                 @RequestParam(required = false) String message,
                                                 @AuthenticationPrincipal UserDetails userDetails) {
        Task task = service.findTaskById(id).getBody();
        if (task != null) {
            String username = userDetails.getUsername();
            if (userDetails.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("COMPANY_ADMIN"))) {
                Admin admin = adminService.findByUsername(username).getBody();
                if (admin != null && admin.getId() == task.getAssignedBy().getId()) {
                    return service.updateTaskStatus(id, status, message);
                }
            } else if (userDetails.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("EMPLOYEE"))) {
                Employee employee = employeeService.findByUsername(username).getBody();
                if (employee != null && employee.getId() == task.getEmployee().getId()) {
                    return service.updateTaskStatus(id, status, message);
                }
            }
            throw new IllegalStateException("You are not authorized to update this task");
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/updateMessage")
    @PreAuthorize("hasRole('COMPANY_ADMIN')")
    public ResponseEntity<Task> updateTaskMessage(@RequestParam int id, @RequestParam String message) {
        return service.updateTaskMessage(id, message);
    }
}