package com.kronospan.aibi.repository;

import com.kronospan.aibi.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
    
    List<Document> findByDocumentType(String documentType);
    
    List<Document> findByDocumentCategory(String documentCategory);
    
    List<Document> findByProcessingStatus(String processingStatus);
    
    List<Document> findByCompanyId(Long companyId);
    
    @Query("SELECT d FROM Document d WHERE d.extractedText LIKE %:searchTerm%")
    List<Document> findByExtractedTextContaining(@Param("searchTerm") String searchTerm);
    
    @Query("SELECT d FROM Document d WHERE d.documentName LIKE %:name%")
    List<Document> findByDocumentNameContaining(@Param("name") String name);
}