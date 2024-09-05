package com.techlabs.jpacrud.service;


import java.util.Optional;

import org.springframework.data.domain.Page;

import com.techlabs.jpacrud.dto.PageResponseDto;
import com.techlabs.jpacrud.entity.Student;

public interface StudentService {
	
	public PageResponseDto getAllStudents(int pageSize, int pageNumber);
	public Student getStudentById(int rollnumber);
	public void addStudent(Student student);
	public PageResponseDto getStudentByName(String name, int pageNumber,int pageSize);

}
