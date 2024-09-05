package com.techlabs.document.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techlabs.document.entity.Document;

public interface DocumentRepository extends JpaRepository<Document,Integer>{

}
