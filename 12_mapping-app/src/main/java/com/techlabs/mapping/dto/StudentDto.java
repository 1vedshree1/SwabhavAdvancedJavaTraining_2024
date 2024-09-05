package com.techlabs.mapping.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class StudentDto {
	
	 private int rollNumber;
	
	 private String name;
	
	 private int age;

}
