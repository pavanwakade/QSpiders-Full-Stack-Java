package com.qsp.springbootCompany.service;

import com.qsp.springbootCompany.dao.TaskDao;
import com.qsp.springbootCompany.dto.Task;
import com.qsp.springbootCompany.exception.IdNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskDao dao;

    public ResponseEntity<Task> saveTask(Task task) {
        Task savedTask = dao.saveTask(task);
        return new ResponseEntity<>(savedTask, HttpStatus.CREATED);
    }

    public ResponseEntity<String> deleteTask(int id) {
        Optional<Task> optional = dao.findTaskById(id);
        if (optional.isPresent()) {
            dao.deleteTask(id);
            return new ResponseEntity<>("Task deleted", HttpStatus.OK);
        }
        throw new IdNotFoundException();
    }

    public ResponseEntity<List<Task>> findTasksByEmployeeId(int employeeId) {
        List<Task> tasks = dao.findTasksByEmployeeId(employeeId);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    public ResponseEntity<List<Task>> findAll() {
        List<Task> tasks = dao.findAll();
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    public ResponseEntity<Task> findTaskById(int id) { // Added to support task retrieval by ID
        Optional<Task> optional = dao.findTaskById(id);
        if (optional.isPresent()) {
            return new ResponseEntity<>(optional.get(), HttpStatus.OK);
        }
        throw new IdNotFoundException("Task with ID " + id + " not found");
    }

    public ResponseEntity<Task> updateTaskStatus(int id, String status, String message) {
        Task updatedTask = dao.updateTaskStatus(id, status, message);
        if (updatedTask != null) {
            return new ResponseEntity<>(updatedTask, HttpStatus.OK);
        }
        throw new IdNotFoundException("Task with ID " + id + " not found");
    }

    public ResponseEntity<Task> updateTaskMessage(int id, String message) {
        Task updatedTask = dao.updateTaskMessage(id, message);
        if (updatedTask != null) {
            return new ResponseEntity<>(updatedTask, HttpStatus.OK);
        }
        throw new IdNotFoundException("Task with ID " + id + " not found");
    }
}