package com.qsp.springboot.Student;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class StudentController {

	@Autowired
	StudentRepository repository;

	@PostMapping("/student")
	public Student saveStudent(@RequestBody Student stu) {

		Student s = repository.save(stu);

		return s;
	}

	@PutMapping("/student")
	public Student updateStudent(@RequestBody Student stu) {

		Student s = repository.save(stu);

		return s;
	}

	@DeleteMapping("/student")
	public String deleteStudent(@RequestParam int id) {

		Optional<Student> optional = repository.findById(id);

		if (optional.isPresent()) {

			Student ss = optional.get();

			repository.delete(ss);

			return "Student delete sucessfully with id: " + id;
		}
		return "Student with id: " + id + " Not Found";
	}
	
	
	@GetMapping("/student")
	public List<Student> findAllStudent() {

		List<Student> list = repository.findAll();
		
		return list;
	}
	
	@GetMapping("/student/{id}")
	public Student StudentfindById(@PathVariable int id) {
		
		Optional<Student>optional=repository.findById(id);
		
		if (optional.isPresent()) {
			
			Student e=optional.get();
			
			return e;
		}
		
		return null;
		
	}
	
}
