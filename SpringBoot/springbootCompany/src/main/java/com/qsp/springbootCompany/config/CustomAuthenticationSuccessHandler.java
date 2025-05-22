package com.qsp.springbootCompany.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        String redirectURL = "";
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (GrantedAuthority authority : authorities) {
            switch (authority.getAuthority()) {
                case "PORTAL_ADMIN":
                    redirectURL = "/portal-admin-dashboard.html";
                    break;
                case "COMPANY_ADMIN":
                    redirectURL = "/admin-dashboard.html";
                    break;
                case "EMPLOYEE":
                    redirectURL = "/employee-dashboard.html";
                    break;
            }
        }
        response.sendRedirect(redirectURL);
    }
}