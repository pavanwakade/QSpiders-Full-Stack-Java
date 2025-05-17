package com.qsp.springbootCompany.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
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
	
	
	public List<Trainer> findBySubject(String subject) {
		return repository.findBySubject(subject);
	}
	
	
	public List<Trainer> findAllTrainer(Trainer trainer){
		
		return repository.findAll();
	}
	
	public List<Trainer>findByNAme(String name){
		return repository.findByName(name);
		
	}
	
	public void deleteTrainer(int id) {
		 repository.deleteById(id);
	}
	
	
	
}
