package com.techlabs.jpacrud.dto;

import java.util.List;

import com.techlabs.jpacrud.entity.Student;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@AllArgsConstructor
@Data

public class PageResponseDto {
	
	private long totalElements;
	private int totalPages;
	private int size;
	private List<Student> content;
	public boolean IslastPage;
		// TODO Auto-generated method stub
		
	

}
