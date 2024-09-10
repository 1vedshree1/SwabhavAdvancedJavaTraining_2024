package com.techlabs.bank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.techlabs.bank.entity.Document;
import com.techlabs.bank.entity.DocumentStatus;
import com.techlabs.bank.service.DocumentService;

@RestController
@RequestMapping("/bankapp/documents")
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @PostMapping("/upload/{customerId}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<Document> uploadDocument(@PathVariable int customerId,
                                                   @RequestParam("file") MultipartFile file,
                                                   @RequestParam("documentName") String documentName) {
        Document document = documentService.uploadDocument(customerId, file, documentName);
        return ResponseEntity.ok(document);
    }

    @GetMapping("/customer/{customerId}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<List<Document>> getDocumentsByCustomerId(@PathVariable int customerId) {
        List<Document> documents = documentService.getDocumentsByCustomerId(customerId);
        return ResponseEntity.ok(documents);
    }

    @GetMapping("/download/{documentId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<byte[]> downloadDocument(@PathVariable int documentId) {
        byte[] documentData = documentService.downloadDocument(documentId);

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=document_" + documentId);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        return new ResponseEntity<>(documentData, headers, HttpStatus.OK);
    }

    @PutMapping("/status/{documentId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Document> updateDocumentStatus(@PathVariable int documentId,
                                                         @RequestParam("status") DocumentStatus status) {
        Document updatedDocument = documentService.updateDocumentStatus(documentId, status);
        return ResponseEntity.ok(updatedDocument);
    }
}
