package com.techlabs.mapping.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.techlabs.mapping.dto.PageResponseDto;
import com.techlabs.mapping.dto.StudentDto;
import com.techlabs.mapping.entity.Address;
import com.techlabs.mapping.entity.Course;
import com.techlabs.mapping.entity.Student;



public interface StudentService {

//	public PageResponseDto<Student> getAllStudents(int pageNumber, int pageSize);
	public Student addStudents(Student student);
	public Optional<Student> getStudentById(int rollnumber);
	

	public Optional<Address> getAddressByRollNumber(int rollnumber);
	Address updateStudentAddress(int rollnumber, Address address);
	
	public StudentDto getStudentByRollNumber(int rollNumber);
	PageResponseDto<StudentDto> getAllStudents(int pageNumber, int pageSize);
	StudentDto assignCourses (int rollNumber, List<Integer> courses );
	Set<Student> getStudentsByInstructor(int instructorId);
	
}

