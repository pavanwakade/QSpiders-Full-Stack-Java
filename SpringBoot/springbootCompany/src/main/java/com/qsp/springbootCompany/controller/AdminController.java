package com.qsp.springbootCompany.controller;

  import com.qsp.springbootCompany.dto.Admin;
  import com.qsp.springbootCompany.dto.AdminLoginDTO;
  import com.qsp.springbootCompany.service.AdminService;
  import com.qsp.springbootCompany.service.CompanyService;
  import org.springframework.beans.factory.annotation.Autowired;
  import org.springframework.http.ResponseEntity;
  import org.springframework.web.bind.annotation.*;

  import java.util.List;

  @RestController
  @RequestMapping("/admin")
  public class AdminController {

      @Autowired
      private AdminService adminService;

      @Autowired
      private CompanyService companyService;

      @PostMapping("/register")
      public ResponseEntity<Admin> register(@RequestBody Admin admin) {
          if (admin.getCompany() != null && companyService.findCompanyById(admin.getCompany().getId()).getBody().isApproved()) {
              return adminService.saveAdmin(admin);
          }
          throw new IllegalStateException("Company is not approved");
      }

      @GetMapping("/findById")
      public ResponseEntity<Admin> findAdminById(@RequestParam int id) {
          return adminService.findAdminById(id);
      }

      @GetMapping("/findAll")
      public ResponseEntity<List<Admin>> findAll() {
          return adminService.findAll();
      }

      @PostMapping("/login")
      public ResponseEntity<AdminLoginDTO> login(@RequestBody Admin admin) {
          Admin foundAdmin = adminService.login(admin.getUsername(), admin.getPassword()).getBody();
          AdminLoginDTO dto = new AdminLoginDTO(
              foundAdmin.getId(),
              foundAdmin.getUsername(),
              foundAdmin.getCompany().getId()
          );
          return ResponseEntity.ok(dto);
      }

      @DeleteMapping("/delete")
      public ResponseEntity<String> deleteAdmin(@RequestParam int id) {
          return adminService.deleteAdmin(id);
      }
  }