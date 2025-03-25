package com.qsp.springboot.service;

import java.util.List;
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

	public ResponseEntity<Person> saveperson(Person p) {

		Person retp = dao.savePerson(p);

		return new ResponseEntity<Person>(retp, HttpStatus.CREATED);
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

	public ResponseEntity<List<Person>> findAllPerson() {

		List<Person> persons = dao.findAll();
		return new ResponseEntity<List<Person>>(persons, HttpStatus.OK);
	}

	public ResponseEntity<Person> deletePerson(int id) {

		Optional<Person> person2 = dao.findById(id);

		if (person2.isPresent()) {
			dao.deletePerson(id);

			return new ResponseEntity<Person>(HttpStatus.OK);
		}
		return new ResponseEntity<Person>(HttpStatus.NOT_FOUND);

	}

	public ResponseEntity<Person> findByPhone(long phone) {

		Person person = null;

		Optional<Person> optional = dao.findByPhone(phone);

		if (optional.isPresent())

		{
			person = optional.get();
			return new ResponseEntity<Person>(person, HttpStatus.OK);
		}

		return new ResponseEntity<Person>(person, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
