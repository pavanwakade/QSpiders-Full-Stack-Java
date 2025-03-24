package com.qsp.springboot.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.qsp.springboot.dao.PersonDao;
import com.qsp.springboot.entity.Person;

@Service
public class PersonService {

	@Autowired
	PersonDao dao;

	public Person savePerson(Person person) {
		return dao.savePerson(person);
	}

	public ResponseEntity<Person> findById(int id) {

		Person person = null;

		Optional<Person> optional = dao.findById(id);

		if (optional.isPresent())

		{
			person = optional.get();
			return new ResponseEntity<Person>(person, HttpStatus.OK);
		}

		return new ResponseEntity<Person>(person, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	

}
