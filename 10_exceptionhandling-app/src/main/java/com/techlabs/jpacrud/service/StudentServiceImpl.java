package com.techlabs.jpacrud.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.techlabs.jpacrud.dto.PageResponseDto;
import com.techlabs.jpacrud.entity.Student;
import com.techlabs.jpacrud.exception.StudentDoesNotExistException;
import com.techlabs.jpacrud.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService{
	
	@Autowired
	private StudentRepository studentRepo;

	
	
	@Override
	public PageResponseDto getAllStudents(int pageNumber,int pageSize) {
		
		Pageable pageable = PageRequest.of(pageNumber,pageSize);
		
		Page<Student> studentPage = studentRepo.findAll(pageable);
		PageResponseDto studentPageDto = new PageResponseDto();
		studentPageDto.setTotalPages(studentPage.getTotalPages());
		studentPageDto.setTotalElements(studentPage.getTotalElements());
		studentPageDto.setContent(studentPage.getContent());
		studentPageDto.setIslastPage(studentPage.isLast());
		
		
		
		
		return studentPageDto;
	}


	@Override
	public Student getStudentById(int rollnumber) {
		
		Optional<Student> dbStudent = studentRepo.findById(rollnumber);
		if(!dbStudent.isPresent()) {
			throw new StudentDoesNotExistException();
		}
		return dbStudent.get();
		
	}


	@Override
	public void addStudent(Student student) {
		studentRepo.save(student);
		
	}

	@Override
	public PageResponseDto getStudentByName(String name,int pageNumber,int pageSize) {
		// TODO Auto-generated method stub
		Pageable pageable = PageRequest.of(pageNumber,pageSize);
		Page<Student> studentPage = studentRepo.findByName(name, pageable);
		PageResponseDto studentPageDto = new PageResponseDto();
		studentPageDto.setTotalPages(studentPage.getTotalPages());
		studentPageDto.setTotalElements(studentPage.getTotalElements());
		studentPageDto.setContent(studentPage.getContent());
		studentPageDto.setIslastPage(studentPage.isLast());
		return studentPageDto;
	}	
	

}
