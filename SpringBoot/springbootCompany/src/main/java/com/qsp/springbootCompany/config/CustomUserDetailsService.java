package com.qsp.springbootCompany.config;

import com.qsp.springbootCompany.dto.Admin;
import com.qsp.springbootCompany.dto.Employee;
import com.qsp.springbootCompany.dto.PortalAdmin;
import com.qsp.springbootCompany.repository.AdminRepository;
import com.qsp.springbootCompany.repository.EmployeeRepository;
import com.qsp.springbootCompany.repository.PortalAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private PortalAdminRepository portalAdminRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Try to find PortalAdmin
        Optional<UserDetails> user = portalAdminRepository.findByUsername(username)
            .map(UserDetails.class::cast);

        // If not found, try Admin
        if (user.isEmpty()) {
            user = adminRepository.findByUsername(username)
                .map(UserDetails.class::cast);
        }

        // If not found, try Employee
        if (user.isEmpty()) {
            user = employeeRepository.findByUsername(username)
                .map(UserDetails.class::cast);
        }

        // Throw exception if no user is found
        return user.orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }
}