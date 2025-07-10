package com.kronospan.aibi.model;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Document Entity
 * Represents PDF documents and files in the system
 */
@Entity
@Table(name = "documents")
public class Document {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "document_name", nullable = false)
    private String documentName;
    
    @Column(name = "document_type", nullable = false)
    private String documentType; // PDF, Excel, Word, etc.
    
    @Column(name = "file_path")
    private String filePath;
    
    @Column(name = "file_size")
    private Long fileSize;
    
    @Column(name = "content_type")
    private String contentType;
    
    @Lob
    @Column(name = "extracted_text")
    private String extractedText; // Text extracted from PDF/documents
    
    @Column(name = "document_category")
    private String documentCategory; // Financial Statement, NDA, Contract, etc.
    
    @Column(name = "upload_date")
    private LocalDateTime uploadDate;
    
    @Column(name = "last_processed")
    private LocalDateTime lastProcessed;
    
    @Column(name = "processing_status")
    private String processingStatus; // PENDING, PROCESSED, FAILED
    
    @ManyToOne
    @JoinColumn(name = "company_id")
    private GroupCompany company;
    
    // Constructors
    public Document() {}
    
    public Document(String documentName, String documentType, String filePath) {
        this.documentName = documentName;
        this.documentType = documentType;
        this.filePath = filePath;
        this.uploadDate = LocalDateTime.now();
        this.processingStatus = "PENDING";
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getDocumentName() { return documentName; }
    public void setDocumentName(String documentName) { this.documentName = documentName; }
    
    public String getDocumentType() { return documentType; }
    public void setDocumentType(String documentType) { this.documentType = documentType; }
    
    public String getFilePath() { return filePath; }
    public void setFilePath(String filePath) { this.filePath = filePath; }
    
    public Long getFileSize() { return fileSize; }
    public void setFileSize(Long fileSize) { this.fileSize = fileSize; }
    
    public String getContentType() { return contentType; }
    public void setContentType(String contentType) { this.contentType = contentType; }
    
    public String getExtractedText() { return extractedText; }
    public void setExtractedText(String extractedText) { this.extractedText = extractedText; }
    
    public String getDocumentCategory() { return documentCategory; }
    public void setDocumentCategory(String documentCategory) { this.documentCategory = documentCategory; }
    
    public LocalDateTime getUploadDate() { return uploadDate; }
    public void setUploadDate(LocalDateTime uploadDate) { this.uploadDate = uploadDate; }
    
    public LocalDateTime getLastProcessed() { return lastProcessed; }
    public void setLastProcessed(LocalDateTime lastProcessed) { this.lastProcessed = lastProcessed; }
    
    public String getProcessingStatus() { return processingStatus; }
    public void setProcessingStatus(String processingStatus) { this.processingStatus = processingStatus; }
    
    public GroupCompany getCompany() { return company; }
    public void setCompany(GroupCompany company) { this.company = company; }
}