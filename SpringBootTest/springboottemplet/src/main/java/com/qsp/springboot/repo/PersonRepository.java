package com.qsp.springboot.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qsp.springboot.entity.Person;

public interface PersonRepository extends JpaRepository<Person, Integer> {
	
	public Optional<Person> findByPhone(long phone);

}
