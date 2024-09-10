package com.techlabs.bank.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.techlabs.bank.entity.Customer;
import com.techlabs.bank.entity.Document;
import com.techlabs.bank.entity.DocumentStatus;
import com.techlabs.bank.repository.CustomerRepository;
import com.techlabs.bank.repository.DocumentRepository;

@Service
public class DocumentServiceImpl implements DocumentService {
	
	@Autowired
    private DocumentRepository documentRepo;

    @Autowired
    private CustomerRepository customerRepo;

    @Autowired
    private Cloudinary cloudinary;

	@Override
	public Document uploadDocument(int customerId, MultipartFile file, String documentName) {
		
		 try {
	            // Upload the document to Cloudinary
	            Map<String, Object> uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
	            String documentUrl = (String) uploadResult.get("secure_url");

	            // Create and save the document
	            Document document = new Document();
	            document.setDocumentName(documentName);
	            document.setDocumentUrl(documentUrl);
	            document.setStatus(DocumentStatus.PENDING);

	            Customer customer = customerRepo.findById(customerId)
	                    .orElseThrow(() -> new RuntimeException("Customer not found"));
	            document.setCustomer(customer);

	            return documentRepo.save(document);
	        } catch (IOException e) {
	            throw new RuntimeException("Error uploading file", e);
	        }
	}

	@Override
	public List<Document> getDocumentsByCustomerId(int customerId) {
		// TODO Auto-generated method stub
		return documentRepo.findByCustomer_CustomerId(customerId);
	}

	@Override
	public Document updateDocumentStatus(int documentId, DocumentStatus status) {
		// TODO Auto-generated method stub
		Document document = documentRepo.findById(documentId)
                .orElseThrow(() -> new RuntimeException("Document not found"));
        document.setStatus(status);
        return documentRepo.save(document);
		
	}

	@Override 
	public byte[] downloadDocument(int documentId) {
        Document document = documentRepo.findById(documentId)
                .orElseThrow(() -> new RuntimeException("Document not found"));

        try {
            URL url = new URL(document.getDocumentUrl());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Set request headers (optional)
            connection.setRequestProperty("Accept", "application/octet-stream");

            // Read response
            try (InputStream in = connection.getInputStream()) {
                return in.readAllBytes();
            }
        } catch (IOException e) {
            throw new RuntimeException("Error downloading document", e);
        }
    }
	
	

}
