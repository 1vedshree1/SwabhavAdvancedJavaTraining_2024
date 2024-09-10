package com.techlabs.bank.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class DocumentUploadDTO {
	
	private MultipartFile file;
    private String documentName;

}
