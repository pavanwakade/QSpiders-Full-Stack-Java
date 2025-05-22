package com.qsp.springbootCompany.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserDetailsService userDetailsService;
    private final JwtRequestFilter jwtRequestFilter;

    public SecurityConfig(UserDetailsService userDetailsService, JwtRequestFilter jwtRequestFilter) {
        this.userDetailsService = userDetailsService;
        this.jwtRequestFilter = jwtRequestFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/index.html", "/static/**", 
                                "/admin-login.html", "/admin-register.html", "/admin-dashboard.html",
                                "/employee-login.html", "/employee-register.html", "/employee-dashboard.html",
                                "/portal-admin-login.html", "/portal-admin-register.html", "/portal-admin-dashboard.html",
                                "/company-register.html",
                                "/portal/register", "/portal/login", "/company", "/admin/login", "/employee/login").permitAll()
                .requestMatchers("/portal/**").hasRole("PORTAL_ADMIN")
                .requestMatchers("/admin/**").hasRole("COMPANY_ADMIN")
                .requestMatchers("/employee/**").hasAnyRole("COMPANY_ADMIN", "EMPLOYEE")
                .requestMatchers("/task/**").hasAnyRole("COMPANY_ADMIN", "EMPLOYEE")
                .requestMatchers("/auth/user").authenticated()
                .anyRequest().authenticated()
            )
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}