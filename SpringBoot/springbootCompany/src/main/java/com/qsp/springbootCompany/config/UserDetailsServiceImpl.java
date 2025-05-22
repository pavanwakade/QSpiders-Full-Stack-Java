package com.qsp.springbootCompany.config;

import com.qsp.springbootCompany.dto.Admin;
import com.qsp.springbootCompany.dto.Employee;
import com.qsp.springbootCompany.dto.PortalAdmin;
import com.qsp.springbootCompany.repository.AdminRepository;
import com.qsp.springbootCompany.repository.EmployeeRepository;
import com.qsp.springbootCompany.repository.PortalAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private PortalAdminRepository portalAdminRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Check PortalAdmin
        Optional<PortalAdmin> portalAdmin = portalAdminRepository.findByUsername(username);
        if (portalAdmin.isPresent()) {
            PortalAdmin pa = portalAdmin.get();
            return new User(pa.getUsername(), pa.getPassword(),
                    Collections.singletonList(() -> "ROLE_" + pa.getRole()));
        }

        // Check Admin
        Optional<Admin> admin = adminRepository.findByUsername(username);
        if (admin.isPresent()) {
            Admin a = admin.get();
            return new User(a.getUsername(), a.getPassword(),
                    Collections.singletonList(() -> "ROLE_" + a.getRole()));
        }

        // Check Employee
        Optional<Employee> employee = employeeRepository.findByUsername(username);
        if (employee.isPresent()) {
            Employee e = employee.get();
            return new User(e.getUsername(), e.getPassword(),
                    Collections.singletonList(() -> "ROLE_" + e.getRole()));
        }

        throw new UsernameNotFoundException("User not found with username: " + username);
    }
}