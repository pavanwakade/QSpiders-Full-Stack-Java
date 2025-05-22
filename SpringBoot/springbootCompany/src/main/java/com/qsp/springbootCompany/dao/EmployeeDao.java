package com.qsp.springbootCompany.dao;

import com.qsp.springbootCompany.dto.Employee;
import com.qsp.springbootCompany.dto.Task;
import com.qsp.springbootCompany.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
public class EmployeeDao {

    @Autowired
    private EmployeeRepository repository;

    @Autowired
    private TaskDao taskDao; // Inject TaskDao to manage tasks

    public Employee saveEmployee(Employee employee) {
        return repository.save(employee);
    }

    public Optional<Employee> findEmployeeById(int id) {
        return repository.findById(id);
    }

    public List<Employee> findAll() {
        return repository.findAll();
    }

    public Optional<Employee> findByUsernameAndPassword(String username, String password) {
        return repository.findByUsernameAndPassword(username, password);
    }

    public void deleteEmployee(int id) {
        // Delete all tasks associated with the employee
        List<Task> tasks = taskDao.findTasksByEmployeeId(id);
        for (Task task : tasks) {
            taskDao.deleteTask(task.getId());
        }
        // Now delete the employee
        repository.deleteById(id);
    }
}