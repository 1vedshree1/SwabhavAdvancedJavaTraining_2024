package com.techlabs.mapping.service;

import java.util.List;

import com.techlabs.mapping.dto.InstructorDto;
import com.techlabs.mapping.dto.PageResponseDto;
import com.techlabs.mapping.entity.Instructor;

public interface InstructorService {
	InstructorDto addInstructor(InstructorDto instructorDto);
	
	PageResponseDto<InstructorDto> getInstructors(int pageNumber, int pageSize);
	InstructorDto getInstructorById(int instructorId);
//	List<InstructorDto> getAllInstructors();
	PageResponseDto<InstructorDto> getAllInstructors(int pageNumber, int pageSize);

	Instructor allocateCourses(int instructorId, List<Integer>courseIds);

}
