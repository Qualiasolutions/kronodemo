package com.kronospan.aibi.model;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Director Entity
 * Company directors and board members
 */
@Entity
@Table(name = "directors")
public class Director {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "full_name", nullable = false)
    private String fullName;
    
    @Column(name = "position")
    private String position;
    
    @Column(name = "appointment_date")
    private LocalDate appointmentDate;
    
    @Column(name = "nationality")
    private String nationality;
    
    @Column(name = "is_active")
    private Boolean isActive = true;
    
    @ManyToOne
    @JoinColumn(name = "company_id")
    private GroupCompany company;
    
    // Constructors
    public Director() {}
    
    public Director(String fullName, String position) {
        this.fullName = fullName;
        this.position = position;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    
    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }
    
    public LocalDate getAppointmentDate() { return appointmentDate; }
    public void setAppointmentDate(LocalDate appointmentDate) { this.appointmentDate = appointmentDate; }
    
    public String getNationality() { return nationality; }
    public void setNationality(String nationality) { this.nationality = nationality; }
    
    public Boolean getIsActive() { return isActive; }
    public void setIsActive(Boolean isActive) { this.isActive = isActive; }
    
    public GroupCompany getCompany() { return company; }
    public void setCompany(GroupCompany company) { this.company = company; }
}