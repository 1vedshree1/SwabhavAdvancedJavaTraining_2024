package com.techlabs.mapping.service;

import java.util.List;

import com.techlabs.mapping.dto.CourseDto;
import com.techlabs.mapping.dto.InstructorDto;
import com.techlabs.mapping.dto.PageResponseDto;
import com.techlabs.mapping.dto.StudentDto;
import com.techlabs.mapping.entity.Course;

public interface CourseService {

	public CourseDto addCourse(CourseDto courseDto);

	PageResponseDto<CourseDto> getCourses(int pageNumber, int pageSize);

	CourseDto getCourseById(int courseId);

	List<CourseDto> getAllCourses();

	public Course allocateInstructor(int courseId, int instructorId);

	

	CourseDto assignStudents(int courseId, List<Integer> studentIds);

}
