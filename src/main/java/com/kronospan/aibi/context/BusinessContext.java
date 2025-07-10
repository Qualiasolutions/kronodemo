package com.kronospan.aibi.context;

import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Core Context Engineering Component
 * Manages business terminology, entity recognition, and domain knowledge
 * 
 * Based on Context Engineering principles:
 * - Business terminology dictionary
 * - Entity recognition patterns
 * - Query intent classification
 */
@Component
public class BusinessContext {
    
    // Business terminology mappings
    private final Map<String, String> businessTerms = new ConcurrentHashMap<>();
    
    // Entity type mappings
    private final Map<String, EntityType> entityMappings = new ConcurrentHashMap<>();
    
    // Common query patterns
    private final List<QueryPattern> queryPatterns = new ArrayList<>();
    
    // Currency mappings
    private final Map<String, String> currencyMappings = new ConcurrentHashMap<>();
    
    @PostConstruct
    public void initialize() {
        initializeBusinessTerms();
        initializeEntityMappings();
        initializeQueryPatterns();
        initializeCurrencyMappings();
    }
    
    private void initializeBusinessTerms() {
        // Kronospan-specific business terminology
        businessTerms.put("WCR", "Working Capital Report");
        businessTerms.put("LTL", "Long Term Loans");
        businessTerms.put("RC", "Revolving Credit");
        businessTerms.put("SWAPS", "Interest Rate Swaps");
        businessTerms.put("PKO BP", "Powszechna Kasa Oszczędności Bank Polski");
        businessTerms.put("facility", "credit facility or loan arrangement");
        businessTerms.put("utilization", "amount used divided by total available");
        businessTerms.put("undrawn", "available but not yet used credit");
        businessTerms.put("drawdown", "withdrawal of funds from a credit facility");
        businessTerms.put("exposure", "total amount at risk with a bank or entity");
        businessTerms.put("directorship", "position as a company director");
        businessTerms.put("CY entities", "Cyprus-registered companies");
    }
    
    private void initializeEntityMappings() {
        // Company entities
        entityMappings.put("kronospan asia", EntityType.COMPANY);
        entityMappings.put("oxnard", EntityType.COMPANY);
        entityMappings.put("spanaco", EntityType.COMPANY);
        entityMappings.put("banasino", EntityType.COMPANY);
        
        // Bank entities
        entityMappings.put("pko bp", EntityType.BANK);
        entityMappings.put("erste", EntityType.BANK);
        entityMappings.put("raiffeisen", EntityType.BANK);
        
        // Person entities
        entityMappings.put("matthias kaindl", EntityType.PERSON);
        entityMappings.put("christoforos georgiou", EntityType.PERSON);
        
        // Country entities
        entityMappings.put("poland", EntityType.COUNTRY);
        entityMappings.put("romania", EntityType.COUNTRY);
        entityMappings.put("cyprus", EntityType.COUNTRY);
    }
    
    private void initializeQueryPatterns() {
        // Common Kronospan query patterns
        queryPatterns.add(new QueryPattern(
            "show me all .* with .* over .*",
            QueryIntent.FILTER_QUERY
        ));
        
        queryPatterns.add(new QueryPattern(
            "what are the .* of .* as at .*",
            QueryIntent.POINT_IN_TIME_QUERY
        ));
        
        queryPatterns.add(new QueryPattern(
            "compare .* between .* and .*",
            QueryIntent.COMPARISON_QUERY
        ));
        
        queryPatterns.add(new QueryPattern(
            "which .* have .* over .*",
            QueryIntent.THRESHOLD_QUERY
        ));
        
        queryPatterns.add(new QueryPattern(
            "generate .* report on .*",
            QueryIntent.REPORT_GENERATION
        ));
    }
    
    private void initializeCurrencyMappings() {
        currencyMappings.put("EUR", "Euro");
        currencyMappings.put("PLN", "Polish Zloty");
        currencyMappings.put("BGN", "Bulgarian Lev");
        currencyMappings.put("€", "EUR");
    }
    
    // Public methods for context retrieval
    
    public String expandBusinessTerm(String term) {
        return businessTerms.getOrDefault(term.toUpperCase(), term);
    }
    
    public EntityType identifyEntityType(String entity) {
        return entityMappings.getOrDefault(entity.toLowerCase(), EntityType.UNKNOWN);
    }
    
    public QueryIntent classifyQueryIntent(String query) {
        String lowerQuery = query.toLowerCase();
        for (QueryPattern pattern : queryPatterns) {
            if (lowerQuery.matches(pattern.getPattern())) {
                return pattern.getIntent();
            }
        }
        return QueryIntent.GENERAL_QUERY;
    }
    
    public String normalizeCurrency(String currency) {
        return currencyMappings.getOrDefault(currency.toUpperCase(), "EUR");
    }
    
    // Enums for classification
    
    public enum EntityType {
        COMPANY, BANK, PERSON, COUNTRY, FACILITY_TYPE, UNKNOWN
    }
    
    public enum QueryIntent {
        FILTER_QUERY,
        POINT_IN_TIME_QUERY,
        COMPARISON_QUERY,
        THRESHOLD_QUERY,
        REPORT_GENERATION,
        GENERAL_QUERY
    }
    
    // Inner class for query patterns
    
    private static class QueryPattern {
        private final String pattern;
        private final QueryIntent intent;
        
        public QueryPattern(String pattern, QueryIntent intent) {
            this.pattern = pattern;
            this.intent = intent;
        }
        
        public String getPattern() {
            return pattern;
        }
        
        public QueryIntent getIntent() {
            return intent;
        }
    }
}