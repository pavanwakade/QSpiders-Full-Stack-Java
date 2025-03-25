package com.qsp.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.qsp.springboot.entity.Person;
import com.qsp.springboot.service.PersonService;

@RestController
public class PersonController {

	@Autowired
	PersonService service;

	@PostMapping("/person")
	public ResponseEntity<Person> savePerson(@RequestBody Person person) {
		return service.saveperson(person);
	}

	
	@PostMapping("/update")
	public ResponseEntity<Person> updatePerson(@RequestBody Person person) {
		return service.saveperson(person);
	}

	@GetMapping("/person/{id}")
	public ResponseEntity<Person> getPersonByid(@PathVariable int id) {
		return service.findById(id);

	}

	@GetMapping("/person")
	public ResponseEntity<List<Person>> findAllPerson() {
		return service.findAllPerson();

	}

	@DeleteMapping("/person/{id}")
	public ResponseEntity<Person> deleteById(@PathVariable int id) {
		return service.deletePerson(id);

	}

	@GetMapping("/person/{phone}/phone")
	public ResponseEntity<Person> deleteById(@PathVariable long phone) {
		return service.findByPhone(phone);

	}

	@GetMapping("/hi")
	public String gethii() {
		return "by";
	}

}
