package com.techlabs.db.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.techlabs.db.entity.Student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class StudentRepositoryImpl implements StudentRepository{

	@Autowired
	private EntityManager manager;
	
	@Override
	public List<Student> getAllStudents() {
		
		TypedQuery<Student> query = manager.createQuery("select s from Student s", Student.class);
		
		return query.getResultList();
	}

	@Override
	public Student getStudent(int rollnumber) {
		// TODO Auto-generated method stub
		return manager.find(Student.class,rollnumber );
	}

	@Transactional
	@Override
	public void addStudent(Student student) {
		manager.persist(student);
		
	}

	@Transactional
	@Override
	public void updateStudentById(int rollnumber, Student student) {
		
	Student exstudent = manager.find(Student.class, rollnumber);
	
	exstudent.setName(student.getName());
	exstudent.setAge(student.getAge());
	
	manager.merge(exstudent);
	 
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Student> getStudentsByName(String name) {
		
TypedQuery<Student> query = manager.createQuery("select s from Student s where s.name =:thename", Student.class);
		
query.setParameter("thename",name);
		return query.getResultList();

	}
	
	

}
