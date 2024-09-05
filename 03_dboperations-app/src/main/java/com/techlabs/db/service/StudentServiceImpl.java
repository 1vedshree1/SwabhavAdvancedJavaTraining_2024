package com.techlabs.db.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techlabs.db.entity.Student;
import com.techlabs.db.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService{
	@Autowired
	StudentRepository studentRepo;

	@Override
	public List<Student> getAllStudents() {
		// TODO Auto-generated method stub
		return studentRepo.getAllStudents();
	}

	@Override
	public Student getStudent(int rollnumber) {
		// TODO Auto-generated method stub
		return studentRepo.getStudent(rollnumber);
	}

	@Override
	public void addStudent(Student student) {
		studentRepo.addStudent(student);
		
	}

	@Override
	public void updateStudent(int rollnumber, Student student) {
		studentRepo.updateStudentById(rollnumber, student);
		
	}

	@Override
	public List<Student> getStudentsByName(String name) {
		// TODO Auto-generated method stub
		return studentRepo.getStudentsByName(name);
	}

}
