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
}