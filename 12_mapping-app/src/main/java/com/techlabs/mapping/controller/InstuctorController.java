package com.techlabs.mapping.controller;

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

import com.techlabs.mapping.dto.CourseDto;
import com.techlabs.mapping.dto.InstructorDto;
import com.techlabs.mapping.dto.PageResponseDto;
import com.techlabs.mapping.entity.Instructor;
import com.techlabs.mapping.service.InstructorService;

@RestController
@RequestMapping("/studentapp")
public class InstuctorController {
	@Autowired
	InstructorService instructorservice;
	
	@PostMapping("/instructors")
	public String addInstructor(@RequestBody InstructorDto instructorDto) {
		
		instructorservice.addInstructor(instructorDto);
		return "instructor added successfully";
		
	}
	
//	@GetMapping("/instructors")
//	public ResponseEntity<PageResponseDto> getAllInstuctors(int pageNumber, int pageSize){
//		return ResponseEntity.ok(instructorservice.getInstructors(pageNumber, pageSize));
//		
////	}
//	@GetMapping("/instructors")
//	public ResponseEntity<List<InstructorDto>> getAllInstructors(){
//		return ResponseEntity.ok(instructorservice.getAllInstructors());
//		
//	}
	@GetMapping("/instructors")
	public ResponseEntity<PageResponseDto<InstructorDto>> getAllInstructors(@RequestParam int pageNumber, @RequestParam int pageSize){
		return ResponseEntity.ok(instructorservice.getAllInstructors(pageNumber, pageSize));
		
	}
	@GetMapping("/instructors/{instructorId}")
	public ResponseEntity<InstructorDto> getAllInstuctorById(@PathVariable int instructorId){
		return ResponseEntity.ok(instructorservice.getInstructorById(instructorId));
		
	}
	
	@PutMapping("instructors/courses")
	public ResponseEntity<Instructor> allocateCourses(@RequestParam int instructorId, @RequestBody List<Integer> courseIds){
		return ResponseEntity.ok(instructorservice.allocateCourses(instructorId, courseIds));
	}

}
