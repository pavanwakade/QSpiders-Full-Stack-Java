package com.qsp.springbootCompany.config;

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
        Optional<?> userOpt = portalAdminRepository.findByUsername(username)
            .map(Optional::of)
            .orElseGet(() -> adminRepository.findByUsername(username)
                .map(Optional::of)
                .orElseGet(() -> employeeRepository.findByUsername(username)));

        return (UserDetails) userOpt.orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }
}