package com.techlabs.document.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techlabs.document.entity.Document;
import com.techlabs.document.repository.DocumentRepository;

@Service
public class DocumentServiceImpl implements DocumentService{

	@Autowired
	DocumentRepository documentRepo;
	@Override
	public Document saveDocument(String fileUrl) {
		Document document = new Document();
        document.setFileUrl(fileUrl);
        return documentRepo.save(document);
	}

}
