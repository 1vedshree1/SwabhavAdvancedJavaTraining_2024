package com.techlabs.jpacrud.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.techlabs.jpacrud.entity.Student;

public interface StudentService {
	
	public Page<Student> getAllStudents(int pageSize, int pageNumber);
	public Optional<Student> getStudentById(int rollnumber);
	public void addStudent(Student student);
	public Page<Student> getStudentByName(String name, int pageNumber,int pageSize);

}
