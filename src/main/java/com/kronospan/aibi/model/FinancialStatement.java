package com.kronospan.aibi.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Financial Statement Entity
 * Based on financial PDF data (2013-2017)
 */
@Entity
@Table(name = "financial_statements")
public class FinancialStatement {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "statement_type", nullable = false)
    private String statementType; // Balance Sheet, P&L, Cash Flow
    
    @Column(name = "period_end_date", nullable = false)
    private LocalDate periodEndDate;
    
    @Column(name = "currency", length = 3)
    private String currency;
    
    @Column(name = "total_assets", precision = 15, scale = 2)
    private BigDecimal totalAssets;
    
    @Column(name = "current_assets", precision = 15, scale = 2)
    private BigDecimal currentAssets;
    
    @Column(name = "non_current_assets", precision = 15, scale = 2)
    private BigDecimal nonCurrentAssets;
    
    @Column(name = "total_liabilities", precision = 15, scale = 2)
    private BigDecimal totalLiabilities;
    
    @Column(name = "current_liabilities", precision = 15, scale = 2)
    private BigDecimal currentLiabilities;
    
    @Column(name = "non_current_liabilities", precision = 15, scale = 2)
    private BigDecimal nonCurrentLiabilities;
    
    @Column(name = "total_equity", precision = 15, scale = 2)
    private BigDecimal totalEquity;
    
    @Column(name = "revenue", precision = 15, scale = 2)
    private BigDecimal revenue;
    
    @Column(name = "net_income", precision = 15, scale = 2)
    private BigDecimal netIncome;
    
    @Column(name = "ebitda", precision = 15, scale = 2)
    private BigDecimal ebitda;
    
    @Column(name = "working_capital", precision = 15, scale = 2)
    private BigDecimal workingCapital;
    
    @Column(name = "cash_and_equivalents", precision = 15, scale = 2)
    private BigDecimal cashAndEquivalents;
    
    @ManyToOne
    @JoinColumn(name = "company_id")
    private GroupCompany company;
    
    // Constructors
    public FinancialStatement() {}
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getStatementType() { return statementType; }
    public void setStatementType(String statementType) { this.statementType = statementType; }
    
    public LocalDate getPeriodEndDate() { return periodEndDate; }
    public void setPeriodEndDate(LocalDate periodEndDate) { this.periodEndDate = periodEndDate; }
    
    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }
    
    public BigDecimal getTotalAssets() { return totalAssets; }
    public void setTotalAssets(BigDecimal totalAssets) { this.totalAssets = totalAssets; }
    
    public BigDecimal getCurrentAssets() { return currentAssets; }
    public void setCurrentAssets(BigDecimal currentAssets) { this.currentAssets = currentAssets; }
    
    public BigDecimal getNonCurrentAssets() { return nonCurrentAssets; }
    public void setNonCurrentAssets(BigDecimal nonCurrentAssets) { this.nonCurrentAssets = nonCurrentAssets; }
    
    public BigDecimal getTotalLiabilities() { return totalLiabilities; }
    public void setTotalLiabilities(BigDecimal totalLiabilities) { this.totalLiabilities = totalLiabilities; }
    
    public BigDecimal getCurrentLiabilities() { return currentLiabilities; }
    public void setCurrentLiabilities(BigDecimal currentLiabilities) { this.currentLiabilities = currentLiabilities; }
    
    public BigDecimal getNonCurrentLiabilities() { return nonCurrentLiabilities; }
    public void setNonCurrentLiabilities(BigDecimal nonCurrentLiabilities) { this.nonCurrentLiabilities = nonCurrentLiabilities; }
    
    public BigDecimal getTotalEquity() { return totalEquity; }
    public void setTotalEquity(BigDecimal totalEquity) { this.totalEquity = totalEquity; }
    
    public BigDecimal getRevenue() { return revenue; }
    public void setRevenue(BigDecimal revenue) { this.revenue = revenue; }
    
    public BigDecimal getNetIncome() { return netIncome; }
    public void setNetIncome(BigDecimal netIncome) { this.netIncome = netIncome; }
    
    public BigDecimal getEbitda() { return ebitda; }
    public void setEbitda(BigDecimal ebitda) { this.ebitda = ebitda; }
    
    public BigDecimal getWorkingCapital() { return workingCapital; }
    public void setWorkingCapital(BigDecimal workingCapital) { this.workingCapital = workingCapital; }
    
    public BigDecimal getCashAndEquivalents() { return cashAndEquivalents; }
    public void setCashAndEquivalents(BigDecimal cashAndEquivalents) { this.cashAndEquivalents = cashAndEquivalents; }
    
    public GroupCompany getCompany() { return company; }
    public void setCompany(GroupCompany company) { this.company = company; }
}