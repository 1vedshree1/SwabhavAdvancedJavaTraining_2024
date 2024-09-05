package com.techlabs.mapping.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techlabs.mapping.dto.CourseDto;
import com.techlabs.mapping.dto.PageResponseDto;
import com.techlabs.mapping.dto.StudentDto;
import com.techlabs.mapping.entity.Course;
import com.techlabs.mapping.entity.Instructor;
import com.techlabs.mapping.entity.Student;
import com.techlabs.mapping.repository.CourseRepository;
import com.techlabs.mapping.repository.InstructorRepository;
import com.techlabs.mapping.repository.StudentRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CourseServiceImpl implements CourseService{

	@Autowired
	CourseRepository courseRepo;
	@Autowired 
	InstructorRepository instructorRepo;
	@Autowired
	StudentRepository studentRepo;
	
	private static final Logger logger = LoggerFactory.getLogger(CourseServiceImpl.class);
	@Override
	public CourseDto addCourse(CourseDto courseDto) {
		
		Course course = toCourseMapper(courseDto);
		
		
		course = courseRepo.save(course);
		
		logger.info("Course added successfully: "+course.getCourseId());
		
		return toCourseDtoMapper(course);
	}
	
	public CourseDto toCourseDtoMapper(Course course) {
		CourseDto courseDto = new CourseDto();
		courseDto.setCourseId(course.getCourseId());
		courseDto.setCourseName(course.getCourseName());
		courseDto.setDuration(courseDto.getDuration());
		courseDto.setFees(course.getFees());
		return courseDto;
		
	}
	public Course toCourseMapper(CourseDto courseDto) {
		Course course = new Course();
		course.setCourseName(courseDto.getCourseName());
		course.setDuration(courseDto.getDuration());
		course.setFees(courseDto.getFees());
	
		return course;
		
		
	}

	@Override
	public PageResponseDto<CourseDto> getCourses(int pageNumber, int pageSize) {
		
Pageable pageable = PageRequest.of(pageNumber,pageSize);
		
		Page<Course> coursePage = courseRepo.findAll(pageable);
		PageResponseDto coursePageDto = new PageResponseDto();
		coursePageDto.setTotalPages(coursePage.getTotalPages());
		coursePageDto.setTotalElements(coursePage.getTotalElements());
		coursePageDto.setContent(coursePage.getContent());
		coursePageDto.setIslastPage(coursePage.isLast());
		return coursePageDto;
		
	}

	@Override
	public CourseDto getCourseById(int courseId) {
		
		Optional<Course> optionalCourse= courseRepo.findById(courseId);
		if(!optionalCourse.isPresent()) {
			logger.error("Course with id "+courseId+" not found.");
			return null;
		}
		Course dbCourse = optionalCourse.get();
		
		return toCourseDtoMapper(dbCourse);
	}

	@Override
	public List<CourseDto> getAllCourses() {
		
		List<Course> courseList = courseRepo.findAll();
		List<CourseDto> courseDtoList = new ArrayList<CourseDto>();
		
		CourseDto courseDto = new CourseDto();
		
		for(Course course:courseList) {
			
			courseDto = toCourseDtoMapper(course);
			
			courseDtoList.add(courseDto);
			
		}
		
		return courseDtoList;
	}

	public Course allocateInstructor(int courseId, int instructorId) {
        Optional<Course> optionalCourse = courseRepo.findById(courseId);
        if (!optionalCourse.isPresent()) {
            throw new NullPointerException("Course does not exist.");
        }
        Course dbCourse = optionalCourse.get();
        
        Optional<Instructor> optionalInstructor = instructorRepo.findById(instructorId);
        if (!optionalInstructor.isPresent()) {
            throw new NullPointerException("Instructor does not exist.");
        }
        Instructor instructor = optionalInstructor.get();
        
        dbCourse.setInstructor(instructor);
        
        return courseRepo.save(dbCourse);
    }
	
	@Transactional
    public CourseDto assignStudents(int courseId, List<Integer> studentIds) {
        Course dbCourse = courseRepo.findById(courseId)
            .orElseThrow(() -> new NullPointerException("Course does not exist"));

       
        List<Student> existingStudents = dbCourse.getStudents();
        if (existingStudents == null) {
            existingStudents = new ArrayList<>();
        }

     
        Set<Student> studentsToAdd = new HashSet<>();
        for (Integer studentId : studentIds) {
            Student student = studentRepo.findById(studentId)
                .orElseThrow(() -> new NullPointerException("Student  does not exist"));

           
            studentsToAdd.add(student);
        }

        
        existingStudents.addAll(studentsToAdd);
        dbCourse.setStudents(existingStudents);

        
        for (Student student : studentsToAdd) {
            Set<Course> studentCourses = student.getCourses();
            if (studentCourses == null) {
                studentCourses = new HashSet<>();
            }
            studentCourses.add(dbCourse);
            student.setCourses(studentCourses);
            studentRepo.save(student); 
        }

        
        Course updatedCourse = courseRepo.save(dbCourse);
        
        logger.info("Students assigned successully");

        return toCourseDtoMapper(updatedCourse);
    }

	

}
