package com.techlabs.bank.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "documents")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "document_name")
    private String documentName;

    @Column(name = "document_url")
    private String documentUrl;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "status")
    private DocumentStatus status;  
}
