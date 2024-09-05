package com.techlabs.company.error;

import java.util.List;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class CompanyErrorResponse {
	
	  private int status;
	    private String errorMessage;
	    private long timestamp;
	    private List<FieldError> fieldErrors;

	    @Data
	    @AllArgsConstructor
	    public static class FieldError {
	        private String field;
	        private String message;
	    }
	

}
