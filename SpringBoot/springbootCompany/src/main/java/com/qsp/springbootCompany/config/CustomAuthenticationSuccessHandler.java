package com.qsp.springbootCompany.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_PORTAL_ADMIN"))) {
            response.sendRedirect("/portal-admin-dashboard.html");
        } else if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_COMPANY_ADMIN"))) {
            response.sendRedirect("/admin-dashboard.html");
        } else if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_EMPLOYEE"))) {
            response.sendRedirect("/employee-dashboard.html");
        } else {
            response.sendRedirect("/index.html");
        }
    }
}