package com.techlabs.mapping.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import com.techlabs.mapping.dto.InstructorDto;
import com.techlabs.mapping.dto.PageResponseDto;
import com.techlabs.mapping.entity.Course;
import com.techlabs.mapping.entity.Instructor;

import com.techlabs.mapping.repository.CourseRepository;
import com.techlabs.mapping.repository.InstructorRepository;

@Service
public class InstructorServiceImpl implements InstructorService {

	@Autowired
	InstructorRepository instructorRepo;
	@Autowired
	CourseRepository courseRepo;
	@Override
	public InstructorDto addInstructor(InstructorDto instructorDto) {
		Instructor instructor = toInstuctorMapper(instructorDto);
		
		 
		instructor = instructorRepo.save(instructor);
		
		return toInstructorDtoMapper(instructor);
	}
	
	
	
	
	public InstructorDto toInstructorDtoMapper(Instructor instructor) {
		
		InstructorDto instructorDto = new InstructorDto();
		instructorDto.setEmail(instructor.getEmail());
		instructorDto.setInstructorId(instructor.getInstructorId());
		instructorDto.setQualification(instructor.getQualification());
		instructorDto.setName(instructor.getName());
		return instructorDto;
		
	}
	public Instructor toInstuctorMapper(InstructorDto instructorDto) {
		Instructor instructor = new Instructor();
		instructor.setEmail(instructorDto.getEmail());
		instructor.setName(instructorDto.getName());
		instructor.setQualification(instructorDto.getQualification());
		return instructor;
		
	}




	@Override
	public PageResponseDto<InstructorDto> getInstructors(int pageNumber, int pageSize) {
Pageable pageable = PageRequest.of(pageNumber,pageSize);
		
		Page<Instructor> instructorPage = instructorRepo.findAll(pageable);
		PageResponseDto instructorPageDto = new PageResponseDto();
		instructorPageDto.setTotalPages(instructorPage.getTotalPages());
		instructorPageDto.setTotalElements(instructorPage.getTotalElements());
		instructorPageDto.setContent(instructorPage.getContent());
		instructorPageDto.setIslastPage(instructorPage.isLast());
		return instructorPageDto;
		
		
		
		
	}




	@Override
	public InstructorDto getInstructorById(int instructorId) {
		Optional<Instructor> optionalInstructor=instructorRepo.findById(instructorId);
		if(!optionalInstructor.isPresent())
			return null;
		Instructor dbInstructor=optionalInstructor.get();
		
		return toInstructorDtoMapper(dbInstructor);
	}




//	@Override
//	public List<InstructorDto> getAllInstructors() {
//		List<Instructor> instructorList = instructorRepo.findAll();
//		List<InstructorDto> instructorDtoList = new ArrayList<InstructorDto>();
//		
//		InstructorDto  instructorDto = new InstructorDto();
//		
//		for(Instructor instructor:instructorList) {
//			instructorDto = toInstructorDtoMapper(instructor);
//			instructorDtoList.add(instructorDto);
//			
//		}
//		return instructorDtoList;
//	}
	
public PageResponseDto<InstructorDto> getAllInstructors(int pageNumber, int pageSize) {
		
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<Instructor> instructorPage = instructorRepo.findAll(pageable);
		List<InstructorDto> instructorDtoList = new ArrayList<InstructorDto>();
		InstructorDto instructorDto = new InstructorDto();
		
		for(Instructor instructor:instructorPage.getContent()) {
			instructorDto = toInstructorDtoMapper(instructor);
			instructorDtoList.add(instructorDto);	
		}
		PageResponseDto<InstructorDto> instructorpageDto = new PageResponseDto<InstructorDto>();
		instructorpageDto.setTotalPages(instructorPage.getTotalPages());
		instructorpageDto.setTotalElements(instructorPage.getTotalElements());
		instructorpageDto.setSize(instructorPage.getSize());
		instructorpageDto.setContent(instructorDtoList);
		instructorpageDto.setIslastPage(instructorPage.isLast());
		
		
		return instructorpageDto;
	}




	@Override
	public Instructor allocateCourses(int instructorId, List<Integer> courseIds) {
		
		Instructor dbInstructor = toInstuctorMapper(getInstructorById(instructorId));
		dbInstructor.setInstructorId(instructorId);
		List<Course> dbCourses = dbInstructor.getCourses();
		
		if(dbCourses == null)
			dbCourses = new ArrayList<>();
		
		List<Course> coursesToadd= new ArrayList<>();
		
		courseIds.forEach((courseId)->{
			Optional<Course> optionalCourse = courseRepo.findById(courseId);
			if(!optionalCourse.isPresent())
				throw new NullPointerException("course does not exist.");
			
			Course course = optionalCourse.get();
			course.setInstructor(dbInstructor);
			coursesToadd.add(course);
			});
		
		dbCourses.addAll(coursesToadd);
		dbInstructor.setCourses(dbCourses);
		
		
		// TODO Auto-generated method stub
		return instructorRepo.save(dbInstructor);
	}




	
	
	

}
