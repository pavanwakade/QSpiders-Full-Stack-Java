package com.qsp.springbootCompany.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String description;
    private LocalDate assignedDate;
    // Status can be: "Pending", "InProgress", "Done", "ToDo"
    private String status;
    private String message; // Message from admin

    @ManyToOne
    private Employee employee;

    @ManyToOne
    private Admin assignedBy;

    public Task() {
        super();
    }

    public Task(int id, String description, LocalDate assignedDate, String status, String message, Employee employee, Admin assignedBy) {
        this.id = id;
        this.description = description;
        this.assignedDate = assignedDate;
        this.status = status;
        this.message = message;
        this.employee = employee;
        this.assignedBy = assignedBy;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getAssignedDate() {
        return assignedDate;
    }

    public void setAssignedDate(LocalDate assignedDate) {
        this.assignedDate = assignedDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Admin getAssignedBy() {
        return assignedBy;
    }

    public void setAssignedBy(Admin assignedBy) {
        this.assignedBy = assignedBy;
    }
}