package com.qsp.springboot.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qsp.springboot.entity.Person;

public interface PersonRepository extends JpaRepository<Person, Integer> {

}
