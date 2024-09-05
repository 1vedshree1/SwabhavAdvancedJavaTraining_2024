package com.techlabs.jpacrud.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.techlabs.jpacrud.entity.Student;
import com.techlabs.jpacrud.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService{
	
	@Autowired
	private StudentRepository studentRepo;

	
	
	@Override
	public Page<Student> getAllStudents(int pageNumber,int pageSize) {
		
		Pageable pageable = PageRequest.of(pageNumber,pageSize);
		
		
		return studentRepo.findAll(pageable);
	}


	@Override
	public Optional<Student> getStudentById(int rollnumber) {
		// TODO Auto-generated method stub
		return studentRepo.findById(rollnumber);
	}


	@Override
	public void addStudent(Student student) {
		studentRepo.save(student);
		
	}

	@Override
	public Page<Student> getStudentByName(String name,int pageNumber,int pageSize) {
		// TODO Auto-generated method stub
		Pageable pageable = PageRequest.of(pageNumber,pageSize);
		return studentRepo.findByName(name,pageable);
	}	
	

}
