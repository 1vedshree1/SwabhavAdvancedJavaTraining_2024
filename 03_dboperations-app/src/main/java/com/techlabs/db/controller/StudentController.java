package com.techlabs.db.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techlabs.db.entity.Student;
import com.techlabs.db.service.StudentService;

@RestController
@RequestMapping("/studentapp")
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@GetMapping("/students")
	public ResponseEntity<List<Student>> getAllStudents() {
		return ResponseEntity.ok(studentService.getAllStudents());
	}
	@GetMapping("/students-name")
	public ResponseEntity<List<Student>> getAllStudentsByName(@RequestParam String name) {
		return ResponseEntity.ok(studentService.getStudentsByName(name));
	}
	@GetMapping("/students/{rollnumber}")
	public ResponseEntity<Student> getStudent(@PathVariable int rollnumber){
		return ResponseEntity.ok(studentService.getStudent(rollnumber));
		
	}
	@PostMapping("/students")
	public String addStudent(@RequestBody Student student) {
		studentService.addStudent(student);
		return "student addes successfully";
		
	}
	
	@PutMapping("students/{rollnumber}")
	public String updateStudentById(@PathVariable int rollnumber, @RequestBody Student student) {
		studentService.updateStudent(rollnumber, student);
		
		return "updated successfully";
		
		
	}
	

}
