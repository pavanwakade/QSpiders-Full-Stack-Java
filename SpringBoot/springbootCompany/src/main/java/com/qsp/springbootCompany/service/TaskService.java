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
}