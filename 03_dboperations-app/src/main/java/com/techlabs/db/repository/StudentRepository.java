package com.techlabs.db.repository;

import java.util.List;

import com.techlabs.db.entity.Student;

public interface StudentRepository {
	
	public List<Student> getAllStudents();
	Student getStudent(int rollnumber);
	public void addStudent(Student student);

	public void updateStudentById(int rollnumber, Student student);
	List<Student> getStudentsByName(String name);

}
