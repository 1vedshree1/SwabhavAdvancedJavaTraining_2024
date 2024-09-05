package com.techlabs.jpacrud.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techlabs.jpacrud.dto.PageResponseDto;
import com.techlabs.jpacrud.entity.Student;
import com.techlabs.jpacrud.error.StudentErrorResponse;
import com.techlabs.jpacrud.exception.StudentDoesNotExistException;
import com.techlabs.jpacrud.service.StudentService;

import jakarta.validation.Valid;



@RestController
@RequestMapping("/studentapp")
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@GetMapping("/students")
	public ResponseEntity<PageResponseDto> getAllStudents(@RequestParam(required = false)String name,@RequestParam int pageNumber, @RequestParam int pageSize) {
		if(name!=null) 
			return ResponseEntity.ok(studentService.getStudentByName(name,pageNumber, pageSize ));
		
		return ResponseEntity.ok(studentService.getAllStudents( pageNumber,pageSize));
	}
	
	
	
	
	@GetMapping("/students/{rollnumber}")
	public ResponseEntity<Student> getStudent(@PathVariable int rollnumber){
		return ResponseEntity.ok(studentService.getStudentById(rollnumber));
		
	}
	
	
	
	@PostMapping("/students")
	public String addStudents(@Valid @RequestBody Student student) {
		studentService.addStudent(student);
		return "student added successfully.";
		
	}
	
	

}
