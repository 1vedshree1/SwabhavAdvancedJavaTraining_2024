package com.techlabs.mapping.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techlabs.mapping.entity.Course;

public interface CourseRepository extends JpaRepository<Course,Integer>{
	 List<Course> findByInstructor_InstructorId(int instructorId);

}
