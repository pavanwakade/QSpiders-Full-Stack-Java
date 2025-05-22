package com.qsp.springbootCompany.dao;

import com.qsp.springbootCompany.dto.Task;
import com.qsp.springbootCompany.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
public class TaskDao {

    @Autowired
    private TaskRepository repository;

    public Task saveTask(Task task) {
        return repository.save(task);
    }

    public void deleteTask(int id) {
        repository.deleteById(id);
    }

    public Optional<Task> findTaskById(int id) {
        return repository.findById(id);
    }

    public List<Task> findTasksByEmployeeId(int employeeId) {
        return repository.findByEmployeeId(employeeId);
    }

    public List<Task> findAll() {
        return repository.findAll();
    }

    public Task updateTaskStatus(int id, String status, String message) {
        Optional<Task> optional = repository.findById(id);
        if (optional.isPresent()) {
            Task task = optional.get();
            task.setStatus(status);
            if (message != null) {
                task.setMessage(message);
            }
            return repository.save(task);
        }
        return null;
    }

    public Task updateTaskMessage(int id, String message) {
        Optional<Task> optional = repository.findById(id);
        if (optional.isPresent()) {
            Task task = optional.get();
            task.setMessage(message);
            return repository.save(task);
        }
        return null;
    }
}