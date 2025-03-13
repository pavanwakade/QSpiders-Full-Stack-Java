package com.qsp.config;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan(basePackages = "com.qsp")
public class MyConfig {
	
	@Bean
public EntityManager getEM() {
	
	return Persistence.createEntityManagerFactory("mvc").createEntityManager();
}
	
}
