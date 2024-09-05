package com.techlabs.mapping.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class InstructorDto {
	
	private int instructorId;
	
	private String name;
	
	private String email;
	
	private String qualification;

}
