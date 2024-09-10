package com.techlabs.bank.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.techlabs.bank.entity.Document;
import com.techlabs.bank.entity.DocumentStatus;

public interface DocumentService {
	
	public Document uploadDocument(int customerId, MultipartFile file, String documentName);
	 public List<Document> getDocumentsByCustomerId(int customerId);
	 public Document updateDocumentStatus(int documentId, DocumentStatus status);
	 public byte[] downloadDocument(int documentId);

}
