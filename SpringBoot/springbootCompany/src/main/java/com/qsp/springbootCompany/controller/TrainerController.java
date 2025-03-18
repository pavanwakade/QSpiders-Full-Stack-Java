package com.qsp.springbootCompany.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.qsp.springbootCompany.dto.Trainer;
import com.qsp.springbootCompany.service.TrainerService;

@RestController
public class TrainerController {

	@Autowired
	private TrainerService service;

	@PostMapping("/savetrainer")
	public Trainer saveTrainer(@RequestBody Trainer trainer) {
		return service.saveTrainer(trainer);
	}

	@PostMapping("/updatetrainer")
	public Trainer updateTrainer(@RequestBody Trainer trainer) {
		return service.updateTrainer(trainer);
	}

//	http://localhost:8080/trainerfindbyid?id=3
	@GetMapping("/trainerfindbyid")
	public Trainer findById(@RequestParam int id) {
		return service.TrainerfondById(id);
	}

//	http://localhost:8080/findbysubject?sub=SpringBoot
	@GetMapping("/findbysubject")
	public List<Trainer> findBySubject(@RequestParam String sub) {
		return service.findBySubject(sub);
	}

//	http://localhost:8080/findalltrainer
	@GetMapping("/findalltrainer")
	public List<Trainer> findAllTYrainer(Trainer trainer) {
		return service.findAllTrainer(trainer);
	}

//	http://localhost:8080/findtrainerbyname?name=Tejus
	@GetMapping("/findtrainerbyname")
	public List<Trainer> findByName(@RequestParam String name) {
		return service.findByName(name);

	}

//	http://localhost:8080/deletetrainerbyid?id=3
	@DeleteMapping("/deletetrainerbyid")
	public void deleteTrainerById(@RequestParam int id) {
		service.deleteTrainer(id);

	}

}
