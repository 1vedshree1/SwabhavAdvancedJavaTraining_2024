package com.techlabs.jpacrud.service;

import java.util.List;
import java.util.Optional;

import com.techlabs.jpacrud.entity.Student;

public interface StudentService {
	
	public List<Student> getAllStudents();
	public Optional<Student> getStudentById(int rollnumber);
	public void addStudent(Student student);
	public List<Student> getStudentByName(String name);

}
