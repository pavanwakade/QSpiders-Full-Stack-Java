//package com.qsp.springbootCompany.controller;
//
//import org.springframework.security.core.Authentication;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class HomeController {
//    @GetMapping("/")
//    public String home(Authentication authentication) {
//        if (authentication != null && authentication.isAuthenticated()) {
//            return "Welcome, " + authentication.getName() + "!";
//        }
//        return "redirect:/index.html"; // Redirect to index.html
//    }
//}