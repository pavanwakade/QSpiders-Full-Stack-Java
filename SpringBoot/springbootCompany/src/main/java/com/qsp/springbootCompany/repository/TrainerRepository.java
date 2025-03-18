package com.qsp.springbootCompany.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qsp.springbootCompany.dto.Trainer;

public interface TrainerRepository extends JpaRepository<Trainer, Integer>{
	
	List<Trainer> findBySubject(String subject);
	
	List<Trainer> findByName(String name);
	
	

}
