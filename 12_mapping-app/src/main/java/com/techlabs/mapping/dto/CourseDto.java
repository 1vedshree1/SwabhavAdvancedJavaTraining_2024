package com.techlabs.mapping.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class CourseDto {
	
	private int courseId;
	
	private String courseName;
	
	private int duration;
	
	private double fees;

}
