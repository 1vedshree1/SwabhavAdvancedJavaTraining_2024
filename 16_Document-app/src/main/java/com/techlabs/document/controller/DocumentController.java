package com.techlabs.document.controller;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.techlabs.document.service.DocumentService;

@RestController
@RequestMapping("/documents")
public class DocumentController {
	
	 @Autowired
	    private DocumentService documentService;

	    @Autowired
	    private Cloudinary cloudinary;

	    @PostMapping("/upload")
	    public String uploadDocument(@RequestParam("file") MultipartFile file) {
	        try {
	           
	            String fileUrl = uploadFileToCloudinary(file);

	           
	            documentService.saveDocument(fileUrl);

	            return "File uploaded and URL saved: " + fileUrl;
	        } catch (IOException e) {
	            e.printStackTrace();
	            return "Error uploading file: " + e.getMessage();
	        }
	    }

	    private String uploadFileToCloudinary(MultipartFile file) throws IOException {
	        
	        Map<String, Object> uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());

	        
	        return (String) uploadResult.get("secure_url");
	    }

}
