package com.qsp.springbootCompany.dto;

  import jakarta.persistence.Entity;
  import jakarta.persistence.GeneratedValue;
  import jakarta.persistence.GenerationType;
  import jakarta.persistence.Id;
  import jakarta.persistence.ManyToOne;
  import com.fasterxml.jackson.annotation.JsonBackReference;

  @Entity
  public class Admin {

      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private int id;

      private String username;
      private String password;

      @ManyToOne
      @JsonBackReference // Added to prevent circular reference
      private Company company;

      public Admin() {
          super();
      }

      public Admin(int id, String username, String password, Company company) {
          this.id = id;
          this.username = username;
          this.password = password;
          this.company = company;
      }

      // Getters and Setters
      public int getId() {
          return id;
      }

      public void setId(int id) {
          this.id = id;
      }

      public String getUsername() {
          return username;
      }

      public void setUsername(String username) {
          this.username = username;
      }

      public String getPassword() {
          return password;
      }

      public void setPassword(String password) {
          this.password = password;
      }

      public Company getCompany() {
          return company;
      }

      public void setCompany(Company company) {
          this.company = company;
      }
  }