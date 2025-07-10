package com.kronospan.aibi.model;

import javax.persistence.*;
import java.util.List;

/**
 * Group Company Entity
 * Represents companies in the Kronospan group
 */
@Entity
@Table(name = "group_companies")
public class GroupCompany {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "company_name", nullable = false)
    private String companyName;
    
    @Column(name = "company_code", unique = true)
    private String companyCode;
    
    @Column(name = "country", nullable = false)
    private String country;
    
    @Column(name = "incorporation_country")
    private String incorporationCountry;
    
    @Column(name = "legal_form")
    private String legalForm; // Ltd, SA, etc.
    
    @Column(name = "business_activity")
    private String businessActivity;
    
    @Column(name = "is_active")
    private Boolean isActive = true;
    
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<WorkingCapitalFacility> workingCapitalFacilities;
    
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<LongTermLoan> longTermLoans;
    
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<FinancialStatement> financialStatements;
    
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Director> directors;
    
    // Constructors
    public GroupCompany() {}
    
    public GroupCompany(String companyName, String country) {
        this.companyName = companyName;
        this.country = country;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }
    
    public String getCompanyCode() { return companyCode; }
    public void setCompanyCode(String companyCode) { this.companyCode = companyCode; }
    
    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }
    
    public String getIncorporationCountry() { return incorporationCountry; }
    public void setIncorporationCountry(String incorporationCountry) { this.incorporationCountry = incorporationCountry; }
    
    public String getLegalForm() { return legalForm; }
    public void setLegalForm(String legalForm) { this.legalForm = legalForm; }
    
    public String getBusinessActivity() { return businessActivity; }
    public void setBusinessActivity(String businessActivity) { this.businessActivity = businessActivity; }
    
    public Boolean getIsActive() { return isActive; }
    public void setIsActive(Boolean isActive) { this.isActive = isActive; }
    
    public List<WorkingCapitalFacility> getWorkingCapitalFacilities() { return workingCapitalFacilities; }
    public void setWorkingCapitalFacilities(List<WorkingCapitalFacility> workingCapitalFacilities) { this.workingCapitalFacilities = workingCapitalFacilities; }
    
    public List<LongTermLoan> getLongTermLoans() { return longTermLoans; }
    public void setLongTermLoans(List<LongTermLoan> longTermLoans) { this.longTermLoans = longTermLoans; }
    
    public List<FinancialStatement> getFinancialStatements() { return financialStatements; }
    public void setFinancialStatements(List<FinancialStatement> financialStatements) { this.financialStatements = financialStatements; }
    
    public List<Director> getDirectors() { return directors; }
    public void setDirectors(List<Director> directors) { this.directors = directors; }
    
    @Override
    public String toString() {
        return "GroupCompany{" +
                "id=" + id +
                ", companyName='" + companyName + '\'' +
                ", companyCode='" + companyCode + '\'' +
                ", country='" + country + '\'' +
                ", incorporationCountry='" + incorporationCountry + '\'' +
                '}';
    }
}