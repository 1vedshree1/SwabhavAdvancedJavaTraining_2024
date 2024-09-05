package com.techlabs.jpacrud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techlabs.jpacrud.entity.Student;

public interface StudentRepository extends JpaRepository<Student,Integer>{
	
	public List<Student> findByName(String name);

}
