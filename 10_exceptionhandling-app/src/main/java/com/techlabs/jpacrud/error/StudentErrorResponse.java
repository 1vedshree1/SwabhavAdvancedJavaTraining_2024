package com.techlabs.jpacrud.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class StudentErrorResponse {
	
	private int status;
	private String ErrorMessage;
	private long Timestamp;
	

}
