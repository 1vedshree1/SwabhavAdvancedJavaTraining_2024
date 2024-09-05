package com.techlabs.company.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class PageResponseDto<T> {
	
	private long totalElements;
	private int totalPages;
	private int size;
	private List<T> content;
	public boolean IslastPage;
	

}
