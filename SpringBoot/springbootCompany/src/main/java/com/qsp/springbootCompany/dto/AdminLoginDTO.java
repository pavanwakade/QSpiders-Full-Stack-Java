package com.qsp.springbootCompany.dto;

  public class AdminLoginDTO {
      private int id;
      private String username;
      private int companyId;

      public AdminLoginDTO(int id, String username, int companyId) {
          this.id = id;
          this.username = username;
          this.companyId = companyId;
      }

      // Getters
      public int getId() {
          return id;
      }

      public String getUsername() {
          return username;
      }

      public int getCompanyId() {
          return companyId;
      }
  }