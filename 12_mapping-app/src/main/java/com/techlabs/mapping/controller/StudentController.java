package com.techlabs.mapping.controller;

import java.util.List;
import java.util.Optional;
import java.util.Set;

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

import com.techlabs.mapping.dto.PageResponseDto;
import com.techlabs.mapping.dto.StudentDto;
import com.techlabs.mapping.entity.Address;
import com.techlabs.mapping.entity.Student;
import com.techlabs.mapping.service.StudentService;

@RestController
@RequestMapping("/studentapp")
public class StudentController {
	
	@Autowired 
	StudentService studentservice;
	
	
	@GetMapping("/students")
	public ResponseEntity<PageResponseDto<StudentDto>> getAllStudents(@RequestParam int pageNumber, @RequestParam int pageSize) {
		
		
		return ResponseEntity.ok(studentservice.getAllStudents( pageNumber,pageSize));
	}
	@PostMapping("/students")
    public String addStudents(@RequestBody Student student) {
        System.out.println("Received student: " + student);
        studentservice.addStudents(student);
        return "Student added successfully";
    }
	
	@GetMapping("/students/{rollnumber}")
	public ResponseEntity<StudentDto> getStudent(@PathVariable int rollnumber){
		return ResponseEntity.ok(studentservice.getStudentByRollNumber(rollnumber));
		
	}
	
	@GetMapping("/students/{rollnumber}/address")
    public ResponseEntity<Optional<Address>> getAddressByRollNumber(@PathVariable int rollnumber) {
        return ResponseEntity.ok(studentservice.getAddressByRollNumber(rollnumber));
    }
	
	@PutMapping("/students/address")
	public ResponseEntity<Address> updateAddress(@RequestBody Address address , @RequestParam int rollnumber){
		
		return ResponseEntity.ok(studentservice.updateStudentAddress(rollnumber, address));
		
	}
	
	@PutMapping("/students/course")
	public ResponseEntity<StudentDto> assignCourses(@RequestParam int rollnumber, @RequestBody List<Integer> courseIds){
		return ResponseEntity.ok(studentservice.assignCourses(rollnumber, courseIds));
		
	}
	@GetMapping("/students/instructor")
	public ResponseEntity<Set<Student>> getStudentByInstructor(@RequestParam int instructorID){
		return ResponseEntity.ok(studentservice.getStudentsByInstructor(instructorID));
		
	}
	

	
	
}
