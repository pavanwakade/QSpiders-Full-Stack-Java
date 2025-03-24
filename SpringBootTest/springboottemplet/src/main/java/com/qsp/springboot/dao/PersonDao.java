package com.qsp.springboot.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.qsp.springboot.entity.Person;
import com.qsp.springboot.repo.PersonRepository;

@Component
public class PersonDao {

	@Autowired
	PersonRepository repository;

	public Person savePerson(Person person) {
		return repository.save(person);
	}

	public Optional<Person> findById(int id) {

		return repository.findById(id);
	}

	public List<Person> findAll() {

		return repository.findAll();
	}
	
	public void deletePerson(int id) {

		repository.deleteById(id);
	}
	
	
public Optional<Person> findByPhone(long phone) {

		return repository.findByPhone(phone);
	}

}
