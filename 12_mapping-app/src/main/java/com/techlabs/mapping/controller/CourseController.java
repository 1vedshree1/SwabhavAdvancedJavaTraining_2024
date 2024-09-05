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
import com.techlabs.mapping.dto.PageResponseDto;
import com.techlabs.mapping.dto.StudentDto;
import com.techlabs.mapping.entity.Course;
import com.techlabs.mapping.entity.Instructor;
import com.techlabs.mapping.service.CourseService;

@RestController
@RequestMapping("/studentapp")

public class CourseController {
	@Autowired
	CourseService courseservice;
	
	@PostMapping("/course")
	public String addCourse(@RequestBody CourseDto courseDto) {
		
		courseservice.addCourse(courseDto);
		return "course added successfully";
		
		
	}
//	@GetMapping("/course")
//	public ResponseEntity<PageResponseDto> getAllCourses(int pageNumber, int pageSize){
//		return ResponseEntity.ok(courseservice.getCourses(pageNumber, pageSize));
//		
//	}
	@GetMapping("/course")
	public ResponseEntity<List<CourseDto>> getAllCourses(){
		return ResponseEntity.ok(courseservice.getAllCourses());
		
	}
	
	@GetMapping("/course/{courseId}")
	public ResponseEntity<CourseDto> getAllCourseById(@PathVariable int courseId){
		return ResponseEntity.ok(courseservice.getCourseById(courseId));
		
	}
	
	@PutMapping("/course/instructor")
	public ResponseEntity<Course> allocateInstructors(@RequestParam int courseId, @RequestParam int instructorId){
		return ResponseEntity.ok(courseservice.allocateInstructor(courseId,instructorId));
	}
	
	@PutMapping("/course/student")
	public ResponseEntity<CourseDto> assignStudents(@RequestParam int courseId, @RequestBody List<Integer> studentIds){
		return ResponseEntity.ok(courseservice.assignStudents(courseId, studentIds));
		
	}


}
