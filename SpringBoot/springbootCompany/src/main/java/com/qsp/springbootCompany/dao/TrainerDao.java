package com.qsp.springbootCompany.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.qsp.springbootCompany.dto.Company;
import com.qsp.springbootCompany.dto.Trainer;
import com.qsp.springbootCompany.repository.TrainerRepository;

@Component
public class TrainerDao {

	@Autowired
	private TrainerRepository repository;
	
	
	public Trainer saveTrainer(Trainer trainer) {
		return repository.save(trainer);
	}
	
	public Trainer updateTrainer(Trainer trainer) {
		return repository.save(trainer);
	} 
	
	public Optional<Trainer> findTrainerById(int id) {
		return repository.findById(id);
	}
	
	
	public void deleteTrainer(Trainer trainer) {
		 repository.delete(trainer);
	}
	
	public List<Trainer> findBySubject(String subject) {
		return repository.findBySubject(subject);
	}
	
	
	public List<Trainer> findAllTrainer(Trainer trainer){
		
		return repository.findAll();
	}
	
}
