package com.qsp.springbootCompany.dto;

public class EmployeeLoginDTO {
    private int id;
    private String username;
    private String email;
    private int companyId;

    public EmployeeLoginDTO(int id, String username, String email, int companyId) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.companyId = companyId;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public int getCompanyId() {
        return companyId;
    }
}