package com.kronospan.aibi.model;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Communication Entity
 * Tracks communications, emails, and interactions
 */
@Entity
@Table(name = "communications")
public class Communication {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "communication_type", nullable = false)
    private String communicationType; // EMAIL, PHONE, MEETING, etc.
    
    @Column(name = "subject")
    private String subject;
    
    @Lob
    @Column(name = "content")
    private String content;
    
    @Column(name = "from_party")
    private String fromParty;
    
    @Column(name = "to_party")
    private String toParty;
    
    @Column(name = "communication_date")
    private LocalDateTime communicationDate;
    
    @Column(name = "priority")
    private String priority; // LOW, MEDIUM, HIGH, URGENT
    
    @Column(name = "status")
    private String status; // SENT, RECEIVED, DRAFT, ARCHIVED
    
    @Column(name = "reference_number")
    private String referenceNumber;
    
    @ManyToOne
    @JoinColumn(name = "company_id")
    private GroupCompany company;
    
    @ManyToOne
    @JoinColumn(name = "related_document_id")
    private Document relatedDocument;
    
    // Constructors
    public Communication() {}
    
    public Communication(String communicationType, String subject, String content) {
        this.communicationType = communicationType;
        this.subject = subject;
        this.content = content;
        this.communicationDate = LocalDateTime.now();
        this.status = "RECEIVED";
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getCommunicationType() { return communicationType; }
    public void setCommunicationType(String communicationType) { this.communicationType = communicationType; }
    
    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }
    
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    
    public String getFromParty() { return fromParty; }
    public void setFromParty(String fromParty) { this.fromParty = fromParty; }
    
    public String getToParty() { return toParty; }
    public void setToParty(String toParty) { this.toParty = toParty; }
    
    public LocalDateTime getCommunicationDate() { return communicationDate; }
    public void setCommunicationDate(LocalDateTime communicationDate) { this.communicationDate = communicationDate; }
    
    public String getPriority() { return priority; }
    public void setPriority(String priority) { this.priority = priority; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public String getReferenceNumber() { return referenceNumber; }
    public void setReferenceNumber(String referenceNumber) { this.referenceNumber = referenceNumber; }
    
    public GroupCompany getCompany() { return company; }
    public void setCompany(GroupCompany company) { this.company = company; }
    
    public Document getRelatedDocument() { return relatedDocument; }
    public void setRelatedDocument(Document relatedDocument) { this.relatedDocument = relatedDocument; }
}