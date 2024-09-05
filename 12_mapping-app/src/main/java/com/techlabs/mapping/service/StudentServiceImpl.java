package com.techlabs.mapping.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.techlabs.mapping.dto.InstructorDto;
import com.techlabs.mapping.dto.PageResponseDto;
import com.techlabs.mapping.dto.StudentDto;
import com.techlabs.mapping.entity.Address;
import com.techlabs.mapping.entity.Course;
import com.techlabs.mapping.entity.Instructor;
import com.techlabs.mapping.entity.Student;
import com.techlabs.mapping.repository.CourseRepository;
import com.techlabs.mapping.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService{
	@Autowired
	StudentRepository studentRepo;
	@Autowired
	CourseRepository courseRepo;

	
//public PageResponseDto getAllStudents(int pageNumber,int pageSize) {
//		
//		Pageable pageable = PageRequest.of(pageNumber,pageSize);
//		
//		Page<Student> studentPage = studentRepo.findAll(pageable);
//		PageResponseDto studentPageDto = new PageResponseDto();
//		studentPageDto.setTotalPages(studentPage.getTotalPages());
//		studentPageDto.setTotalElements(studentPage.getTotalElements());
//		studentPageDto.setContent(studentPage.getContent());
//		studentPageDto.setIslastPage(studentPage.isLast());
//		
//		
//		
//		
//		return studentPageDto;	
//	}
	@Override
	public Student addStudents(Student student) {
		// TODO Auto-generated method stub
		return studentRepo.save(student) ;
	}
	
	public Optional<Student> getStudentById(int rollnumber) {
		// TODO Auto-generated method stub
		return studentRepo.findById(rollnumber);
	}
	
	 @Override
	    public Optional<Address> getAddressByRollNumber(int rollnumber) {
	        Optional<Student> studentOpt = studentRepo.findById(rollnumber);
	        return studentOpt.map(Student::getAddress);
	    }
//	@Override
//	public Optional<Student> getStudentByAddress(int rollnumber) {
//		
//		return studentRepo.findByAddress(rollnumber);
//	}
	@Override
	public Address updateStudentAddress(int rollnumber, Address address) {
		Student dbStudent=null;
		Optional<Student> optionalStudent =studentRepo.findById(rollnumber);
		if(!optionalStudent.isPresent())
			return null;
		dbStudent = optionalStudent.get();
		Address dbAddress =dbStudent.getAddress();
		dbAddress.setCity(address.getCity());
		return studentRepo.save(dbStudent).getAddress();
	}
	
	@Override
	public PageResponseDto<StudentDto> getAllStudents(int pageNumber, int pageSize) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<Student> studentPage = studentRepo.findAll(pageable);
		List<StudentDto> studentDtoList = new ArrayList<StudentDto>();
		StudentDto studentDto = new StudentDto();
		
		for(Student student:studentPage.getContent()) {
			studentDto = toStudentDtoMapper(student);
			studentDtoList.add(studentDto);	
		}
		PageResponseDto<StudentDto> studentpageDto = new PageResponseDto<StudentDto>();
		studentpageDto.setTotalPages(studentPage.getTotalPages());
		studentpageDto.setTotalElements(studentPage.getTotalElements());
		studentpageDto.setSize(studentPage.getSize());
		studentpageDto.setContent(studentDtoList);
		studentpageDto.setIslastPage(studentPage.isLast());
		
		
		return studentpageDto;
	}
	@Override
	public StudentDto getStudentByRollNumber(int rollNumber) {
		Optional<Student> optionalStudent=studentRepo.findById(rollNumber);
		if(!optionalStudent.isPresent())
			return null;
		Student dbStudent=optionalStudent.get();
		
		return toStudentDtoMapper(dbStudent);
	}
	@Override
	public StudentDto assignCourses(int rollNumber, List<Integer> courseIds) {
	    
	    Student dbStudent = studentRepo.findById(rollNumber).orElseThrow(()->new NullPointerException("student does not exist"));
	        	

	    
	    Set<Course> existingCourses = dbStudent.getCourses();
	    if (existingCourses == null) {
	        existingCourses = new HashSet<>();
	    }

	    
	    Set<Course> coursesToAdd = new HashSet<>();
	    for (Integer courseId : courseIds) {
	        Course course = courseRepo.findById(courseId)
	            .orElseThrow(() -> new NullPointerException("Course  does not exist"));

	       
	        coursesToAdd.add(course);
	    }

	   
	    existingCourses.addAll(coursesToAdd);
	    dbStudent.setCourses(existingCourses);

	   
	    Student updatedStudent = studentRepo.save(dbStudent);

	    return toStudentDtoMapper(updatedStudent);
	}

	
	
	private StudentDto toStudentDtoMapper(Student student) {
		
		StudentDto studentDto = new StudentDto();
		studentDto.setRollNumber(student.getRollNumber());
		studentDto.setAge(student.getAge());
		studentDto.setName(student.getName());
		return studentDto;
	}
	
	private Student toStudentMapper(StudentDto studentDto) {
		
		Student student = new Student();
		student.setAge(studentDto.getAge());
		student.setName(studentDto.getName());
		
		return student;
		
	}
	
	@Override
	 public Set<Student> getStudentsByInstructor(int instructorId) {
	       
	        List<Course> courses = courseRepo.findByInstructor_InstructorId(instructorId);

	        
	        Set<Student> students = new HashSet<>();
	        for (Course course : courses) {
	            students.addAll(course.getStudents());
	        }

	        return students;
	    }

	

}
