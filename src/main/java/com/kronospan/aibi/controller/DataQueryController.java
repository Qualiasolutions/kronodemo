package com.kronospan.aibi.controller;

import com.kronospan.aibi.model.WorkingCapitalFacility;
import com.kronospan.aibi.model.LongTermLoan;
import com.kronospan.aibi.model.GroupCompany;
import com.kronospan.aibi.repository.WorkingCapitalFacilityRepository;
import com.kronospan.aibi.repository.LongTermLoanRepository;
import com.kronospan.aibi.repository.GroupCompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Data Query Controller
 * Provides REST endpoints to query imported Kronospan data
 */
@RestController
@RequestMapping("/api/v1/data")
@CrossOrigin(origins = "*")
public class DataQueryController {
    
    @Autowired
    private WorkingCapitalFacilityRepository wcrRepository;
    
    @Autowired
    private LongTermLoanRepository ltlRepository;
    
    @Autowired
    private GroupCompanyRepository companyRepository;
    
    /**
     * Get data summary
     * GET /api/v1/data/summary
     */
    @GetMapping("/summary")
    public ResponseEntity<Map<String, Object>> getDataSummary() {
        Map<String, Object> summary = new HashMap<>();
        
        long wcrCount = wcrRepository.count();
        long ltlCount = ltlRepository.count();
        long companyCount = companyRepository.count();
        
        summary.put("working_capital_facilities", wcrCount);
        summary.put("long_term_loans", ltlCount);
        summary.put("group_companies", companyCount);
        summary.put("total_records", wcrCount + ltlCount + companyCount);
        
        // Get top banks by facility count
        if (wcrCount > 0) {
            List<WorkingCapitalFacility> topFacilities = wcrRepository.findAll();
            Map<String, Integer> bankCounts = new HashMap<>();
            
            for (WorkingCapitalFacility facility : topFacilities) {
                bankCounts.put(facility.getBankName(), 
                    bankCounts.getOrDefault(facility.getBankName(), 0) + 1);
            }
            
            summary.put("top_banks", bankCounts);
        }
        
        return ResponseEntity.ok(summary);
    }
    
    /**
     * Get all working capital facilities
     * GET /api/v1/data/wcr
     */
    @GetMapping("/wcr")
    public ResponseEntity<List<WorkingCapitalFacility>> getAllWCRFacilities() {
        List<WorkingCapitalFacility> facilities = wcrRepository.findAll();
        return ResponseEntity.ok(facilities);
    }
    
    /**
     * Get facilities by bank
     * GET /api/v1/data/wcr/bank/{bankName}
     */
    @GetMapping("/wcr/bank/{bankName}")
    public ResponseEntity<List<WorkingCapitalFacility>> getFacilitiesByBank(@PathVariable String bankName) {
        List<WorkingCapitalFacility> facilities = wcrRepository.findByBankName(bankName);
        return ResponseEntity.ok(facilities);
    }
    
    /**
     * Get facilities over amount threshold
     * GET /api/v1/data/wcr/amount/{threshold}
     */
    @GetMapping("/wcr/amount/{threshold}")
    public ResponseEntity<List<WorkingCapitalFacility>> getFacilitiesOverAmount(@PathVariable BigDecimal threshold) {
        List<WorkingCapitalFacility> facilities = wcrRepository.findByLimitAmountGreaterThan(threshold);
        return ResponseEntity.ok(facilities);
    }
    
    /**
     * Get facilities with high utilization
     * GET /api/v1/data/wcr/utilization/{threshold}
     */
    @GetMapping("/wcr/utilization/{threshold}")
    public ResponseEntity<List<WorkingCapitalFacility>> getFacilitiesWithHighUtilization(@PathVariable BigDecimal threshold) {
        List<WorkingCapitalFacility> facilities = wcrRepository.findByUtilizationPercentageGreaterThan(threshold);
        return ResponseEntity.ok(facilities);
    }
    
    /**
     * Get all long term loans
     * GET /api/v1/data/ltl
     */
    @GetMapping("/ltl")
    public ResponseEntity<List<LongTermLoan>> getAllLTLLoans() {
        List<LongTermLoan> loans = ltlRepository.findAll();
        return ResponseEntity.ok(loans);
    }
    
    /**
     * Get loans by lender
     * GET /api/v1/data/ltl/lender/{lenderName}
     */
    @GetMapping("/ltl/lender/{lenderName}")
    public ResponseEntity<List<LongTermLoan>> getLoansByLender(@PathVariable String lenderName) {
        List<LongTermLoan> loans = ltlRepository.findByLenderName(lenderName);
        return ResponseEntity.ok(loans);
    }
    
    /**
     * Get all group companies
     * GET /api/v1/data/companies
     */
    @GetMapping("/companies")
    public ResponseEntity<List<GroupCompany>> getAllCompanies() {
        List<GroupCompany> companies = companyRepository.findAll();
        return ResponseEntity.ok(companies);
    }
    
    /**
     * Get companies by country
     * GET /api/v1/data/companies/country/{country}
     */
    @GetMapping("/companies/country/{country}")
    public ResponseEntity<List<GroupCompany>> getCompaniesByCountry(@PathVariable String country) {
        List<GroupCompany> companies = companyRepository.findByCountry(country);
        return ResponseEntity.ok(companies);
    }
    
    /**
     * Execute natural language demo queries
     * GET /api/v1/data/demo/query/{queryId}
     */
    @GetMapping("/demo/query/{queryId}")
    public ResponseEntity<Map<String, Object>> executeDemoQuery(@PathVariable int queryId) {
        Map<String, Object> result = new HashMap<>();
        
        switch (queryId) {
            case 1:
                // "Show me all facilities with limits over 1 million EUR"
                List<WorkingCapitalFacility> largeFacilities = wcrRepository.findByLimitAmountGreaterThan(BigDecimal.valueOf(1000000));
                result.put("query", "Show me all facilities with limits over 1 million EUR");
                result.put("results", largeFacilities);
                result.put("count", largeFacilities.size());
                break;
                
            case 2:
                // "Which companies have utilization over 80%?"
                List<WorkingCapitalFacility> highUtilization = wcrRepository.findByUtilizationPercentageGreaterThan(BigDecimal.valueOf(80));
                result.put("query", "Which companies have utilization over 80%?");
                result.put("results", highUtilization);
                result.put("count", highUtilization.size());
                break;
                
            case 3:
                // "What are the PKO BP facilities?"
                List<WorkingCapitalFacility> pkoFacilities = wcrRepository.findByBankName("PKO BP");
                result.put("query", "What are the PKO BP facilities?");
                result.put("results", pkoFacilities);
                result.put("count", pkoFacilities.size());
                break;
                
            case 4:
                // "Show me all long term loans"
                List<LongTermLoan> allLoans = ltlRepository.findAll();
                result.put("query", "Show me all long term loans");
                result.put("results", allLoans);
                result.put("count", allLoans.size());
                break;
                
            case 5:
                // "Which are the Cyprus companies?"
                List<GroupCompany> cyprusCompanies = companyRepository.findByCountry("Cyprus");
                result.put("query", "Which are the Cyprus companies?");
                result.put("results", cyprusCompanies);
                result.put("count", cyprusCompanies.size());
                break;
                
            default:
                result.put("error", "Query ID not found. Available queries: 1-5");
                return ResponseEntity.badRequest().body(result);
        }
        
        return ResponseEntity.ok(result);
    }
}