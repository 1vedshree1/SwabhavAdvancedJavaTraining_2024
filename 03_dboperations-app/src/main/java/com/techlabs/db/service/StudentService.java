package com.techlabs.db.service;

import java.util.List;

import com.techlabs.db.entity.Student;

public interface StudentService {
	
	public List<Student> getAllStudents();
	Student getStudent(int rollnumber);
	public void addStudent(Student student);
	public void updateStudent(int rollnumber, Student student);
	List<Student> getStudentsByName(String name);
	

}
