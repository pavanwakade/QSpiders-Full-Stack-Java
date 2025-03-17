package com.qsp.springbootCompany.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qsp.springbootCompany.dao.TrainerDao;
import com.qsp.springbootCompany.dto.Company;
import com.qsp.springbootCompany.dto.Trainer;

@Service
public class TrainerService {

	@Autowired
	private TrainerDao dao;

	public Trainer saveTrainer(Trainer trainer) {
		return dao.saveTrainer(trainer);
	}

	public Trainer updateTrainer(Trainer trainer) {
		return dao.updateTrainer(trainer);
	}

	public Trainer TrainerfondById(int id) {
		Optional<Trainer> optional = dao.findTrainerById(id);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;

	}

	public void deleteTrainer(Trainer trainer) {
		dao.deleteTrainer(trainer);
	}
	
	
	public List<Trainer> findBySubject(String subject) {
		return dao.findBySubject(subject);
		
	}
	
	
	public List<Trainer> findAllTrainer(Trainer trainer){
		return dao.findAllTrainer(trainer);
	}
	
	public List<Trainer> findByName(String name){
		return dao.findByNAme(name);
	}
}
