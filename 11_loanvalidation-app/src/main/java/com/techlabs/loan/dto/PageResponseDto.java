package com.techlabs.loan.dto;

import java.util.List;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Data
@AllArgsConstructor

public class PageResponseDto<T>{
	
	private long totalElements;
	private int totalPages;
	private int size;
	private List<T> content;
	public boolean IslastPage;
		// TODO Auto-generated method stub
		
	

}
