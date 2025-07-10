package com.kronospan.aibi.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Long Term Loan Entity
 * Based on LTL_Data.xlsx
 */
@Entity
@Table(name = "long_term_loans")
public class LongTermLoan {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "loan_reference", nullable = false)
    private String loanReference;
    
    @Column(name = "lender_name", nullable = false)
    private String lenderName;
    
    @Column(name = "loan_type")
    private String loanType;
    
    @Column(name = "currency", length = 3)
    private String currency;
    
    @Column(name = "original_amount", precision = 15, scale = 2)
    private BigDecimal originalAmount;
    
    @Column(name = "outstanding_amount", precision = 15, scale = 2)
    private BigDecimal outstandingAmount;
    
    @Column(name = "interest_rate", precision = 6, scale = 4)
    private BigDecimal interestRate;
    
    @Column(name = "start_date")
    private LocalDate startDate;
    
    @Column(name = "maturity_date")
    private LocalDate maturityDate;
    
    @Column(name = "repayment_frequency")
    private String repaymentFrequency;
    
    @Column(name = "security_type")
    private String securityType;
    
    @Column(name = "purpose")
    private String purpose;
    
    @ManyToOne
    @JoinColumn(name = "company_id")
    private GroupCompany company;
    
    // Constructors
    public LongTermLoan() {}
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getLoanReference() { return loanReference; }
    public void setLoanReference(String loanReference) { this.loanReference = loanReference; }
    
    public String getLenderName() { return lenderName; }
    public void setLenderName(String lenderName) { this.lenderName = lenderName; }
    
    public String getLoanType() { return loanType; }
    public void setLoanType(String loanType) { this.loanType = loanType; }
    
    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }
    
    public BigDecimal getOriginalAmount() { return originalAmount; }
    public void setOriginalAmount(BigDecimal originalAmount) { this.originalAmount = originalAmount; }
    
    public BigDecimal getOutstandingAmount() { return outstandingAmount; }
    public void setOutstandingAmount(BigDecimal outstandingAmount) { this.outstandingAmount = outstandingAmount; }
    
    public BigDecimal getInterestRate() { return interestRate; }
    public void setInterestRate(BigDecimal interestRate) { this.interestRate = interestRate; }
    
    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
    
    public LocalDate getMaturityDate() { return maturityDate; }
    public void setMaturityDate(LocalDate maturityDate) { this.maturityDate = maturityDate; }
    
    public String getRepaymentFrequency() { return repaymentFrequency; }
    public void setRepaymentFrequency(String repaymentFrequency) { this.repaymentFrequency = repaymentFrequency; }
    
    public String getSecurityType() { return securityType; }
    public void setSecurityType(String securityType) { this.securityType = securityType; }
    
    public String getPurpose() { return purpose; }
    public void setPurpose(String purpose) { this.purpose = purpose; }
    
    public GroupCompany getCompany() { return company; }
    public void setCompany(GroupCompany company) { this.company = company; }
}