package com.kronospan.aibi.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Working Capital Facility Entity
 * Based on WCR Excel data with 358 rows of facility information
 */
@Entity
@Table(name = "working_capital_facilities")
public class WorkingCapitalFacility {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "facility_name", nullable = false)
    private String facilityName;
    
    @Column(name = "bank_name", nullable = false)
    private String bankName;
    
    @Column(name = "facility_type")
    private String facilityType; // RC, SWAPS, etc.
    
    @Column(name = "currency", length = 3)
    private String currency; // EUR, PLN, BGN
    
    @Column(name = "limit_amount", precision = 15, scale = 2)
    private BigDecimal limitAmount;
    
    @Column(name = "utilized_amount", precision = 15, scale = 2)
    private BigDecimal utilizedAmount;
    
    @Column(name = "undrawn_amount", precision = 15, scale = 2)
    private BigDecimal undrawnAmount;
    
    @Column(name = "utilization_percentage", precision = 5, scale = 2)
    private BigDecimal utilizationPercentage;
    
    @Column(name = "report_date")
    private LocalDate reportDate;
    
    @Column(name = "maturity_date")
    private LocalDate maturityDate;
    
    @Column(name = "interest_rate", precision = 6, scale = 4)
    private BigDecimal interestRate;
    
    @ManyToOne
    @JoinColumn(name = "company_id")
    private GroupCompany company;
    
    // Constructors
    public WorkingCapitalFacility() {}
    
    public WorkingCapitalFacility(String facilityName, String bankName, String facilityType) {
        this.facilityName = facilityName;
        this.bankName = bankName;
        this.facilityType = facilityType;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getFacilityName() { return facilityName; }
    public void setFacilityName(String facilityName) { this.facilityName = facilityName; }
    
    public String getBankName() { return bankName; }
    public void setBankName(String bankName) { this.bankName = bankName; }
    
    public String getFacilityType() { return facilityType; }
    public void setFacilityType(String facilityType) { this.facilityType = facilityType; }
    
    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }
    
    public BigDecimal getLimitAmount() { return limitAmount; }
    public void setLimitAmount(BigDecimal limitAmount) { this.limitAmount = limitAmount; }
    
    public BigDecimal getUtilizedAmount() { return utilizedAmount; }
    public void setUtilizedAmount(BigDecimal utilizedAmount) { this.utilizedAmount = utilizedAmount; }
    
    public BigDecimal getUndrawnAmount() { return undrawnAmount; }
    public void setUndrawnAmount(BigDecimal undrawnAmount) { this.undrawnAmount = undrawnAmount; }
    
    public BigDecimal getUtilizationPercentage() { return utilizationPercentage; }
    public void setUtilizationPercentage(BigDecimal utilizationPercentage) { this.utilizationPercentage = utilizationPercentage; }
    
    public LocalDate getReportDate() { return reportDate; }
    public void setReportDate(LocalDate reportDate) { this.reportDate = reportDate; }
    
    public LocalDate getMaturityDate() { return maturityDate; }
    public void setMaturityDate(LocalDate maturityDate) { this.maturityDate = maturityDate; }
    
    public BigDecimal getInterestRate() { return interestRate; }
    public void setInterestRate(BigDecimal interestRate) { this.interestRate = interestRate; }
    
    public GroupCompany getCompany() { return company; }
    public void setCompany(GroupCompany company) { this.company = company; }
    
    @Override
    public String toString() {
        return "WorkingCapitalFacility{" +
                "id=" + id +
                ", facilityName='" + facilityName + '\'' +
                ", bankName='" + bankName + '\'' +
                ", facilityType='" + facilityType + '\'' +
                ", currency='" + currency + '\'' +
                ", limitAmount=" + limitAmount +
                ", utilizedAmount=" + utilizedAmount +
                '}';
    }
}