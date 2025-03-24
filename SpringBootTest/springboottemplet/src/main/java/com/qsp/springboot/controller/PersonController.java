package com.qsp.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.qsp.springboot.entity.Person;
import com.qsp.springboot.service.PersonService;


@RestController
public class PersonController {

	@Autowired
	PersonService service;
	
	
	@GetMapping("/person")
	public Person savePerson(@RequestParam Person person) {
		return service.savePerson(person);
	}
	
	@GetMapping("/person/{id}")
	public ResponseEntity<Person> getPersonByid()
	
}
