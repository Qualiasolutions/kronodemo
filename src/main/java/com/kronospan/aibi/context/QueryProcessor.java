package com.kronospan.aibi.context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * Context Engineering Query Processor
 * 
 * Core component that processes natural language queries and converts them to SQL
 * using business context and domain knowledge
 */
@Component
public class QueryProcessor {
    
    @Autowired
    private BusinessContext businessContext;
    
    /**
     * Process a natural language query and convert to SQL
     * 
     * @param naturalLanguageQuery The user's natural language query
     * @return QueryResult containing SQL, context, and metadata
     */
    public QueryResult processQuery(String naturalLanguageQuery) {
        // Step 1: Clean and normalize the query
        String normalizedQuery = normalizeQuery(naturalLanguageQuery);
        
        // Step 2: Identify entities and business terms
        Map<String, Object> contextData = extractContext(normalizedQuery);
        
        // Step 3: Classify query intent
        BusinessContext.QueryIntent intent = businessContext.classifyQueryIntent(normalizedQuery);
        
        // Step 4: Generate SQL based on context and intent
        String sql = generateSQL(normalizedQuery, contextData, intent);
        
        // Step 5: Create result with metadata
        QueryResult result = new QueryResult();
        result.setOriginalQuery(naturalLanguageQuery);
        result.setNormalizedQuery(normalizedQuery);
        result.setGeneratedSQL(sql);
        result.setQueryIntent(intent);
        result.setContextData(contextData);
        result.setProcessingTimeMs(System.currentTimeMillis());
        
        return result;
    }
    
    /**
     * Normalize query by expanding business terms and cleaning text
     */
    private String normalizeQuery(String query) {
        String normalized = query.toLowerCase().trim();
        
        // Expand business terms
        normalized = normalized.replace("wcr", "working capital report");
        normalized = normalized.replace("ltl", "long term loan");
        normalized = normalized.replace("rc", "revolving credit");
        normalized = normalized.replace("pko bp", "Powszechna Kasa Oszczędności Bank Polski");
        
        // Normalize common phrases
        normalized = normalized.replace("show me", "select");
        normalized = normalized.replace("what are", "select");
        normalized = normalized.replace("tell me", "select");
        normalized = normalized.replace("give me", "select");
        
        return normalized;
    }
    
    /**
     * Extract context from the query - identify entities, amounts, dates, etc.
     */
    private Map<String, Object> extractContext(String query) {
        Map<String, Object> context = new HashMap<>();
        
        // Extract entities
        if (query.contains("kronospan asia") || query.contains("kronospan")) {
            context.put("entity_type", "company");
            context.put("entity_name", "kronospan asia");
        }
        
        if (query.contains("oxnard")) {
            context.put("entity_type", "company");
            context.put("entity_name", "oxnard");
        }
        
        if (query.contains("pko bp") || query.contains("erste") || query.contains("raiffeisen")) {
            context.put("has_bank_filter", true);
        }
        
        // Extract amount filters
        if (query.contains("over") || query.contains("above") || query.contains("greater than")) {
            context.put("has_amount_filter", true);
            context.put("amount_operator", ">");
        }
        
        if (query.contains("under") || query.contains("below") || query.contains("less than")) {
            context.put("has_amount_filter", true);
            context.put("amount_operator", "<");
        }
        
        // Extract currency
        if (query.contains("eur") || query.contains("euro") || query.contains("€")) {
            context.put("currency", "EUR");
        } else if (query.contains("pln") || query.contains("zloty")) {
            context.put("currency", "PLN");
        }
        
        // Extract time context
        if (query.contains("2023") || query.contains("2024") || query.contains("2025")) {
            context.put("has_date_filter", true);
        }
        
        return context;
    }
    
    /**
     * Generate SQL query based on context and intent
     */
    private String generateSQL(String query, Map<String, Object> context, BusinessContext.QueryIntent intent) {
        StringBuilder sql = new StringBuilder();
        
        // Start with basic SELECT based on intent
        switch (intent) {
            case FILTER_QUERY:
                sql.append("SELECT * FROM ");
                break;
            case THRESHOLD_QUERY:
                sql.append("SELECT * FROM ");
                break;
            case COMPARISON_QUERY:
                sql.append("SELECT ");
                break;
            case REPORT_GENERATION:
                sql.append("SELECT ");
                break;
            default:
                sql.append("SELECT * FROM ");
        }
        
        // Determine main table
        if (query.contains("facility") || query.contains("wcr") || query.contains("working capital")) {
            sql.append("working_capital_facilities wcf ");
            sql.append("JOIN group_companies gc ON wcf.company_id = gc.id ");
        } else if (query.contains("loan") || query.contains("ltl") || query.contains("long term")) {
            sql.append("long_term_loans ltl ");
            sql.append("JOIN group_companies gc ON ltl.company_id = gc.id ");
        } else if (query.contains("financial") || query.contains("statement") || query.contains("balance")) {
            sql.append("financial_statements fs ");
            sql.append("JOIN group_companies gc ON fs.company_id = gc.id ");
        } else if (query.contains("director") || query.contains("board")) {
            sql.append("directors d ");
            sql.append("JOIN group_companies gc ON d.company_id = gc.id ");
        } else {
            // Default to working capital facilities
            sql.append("working_capital_facilities wcf ");
            sql.append("JOIN group_companies gc ON wcf.company_id = gc.id ");
        }
        
        // Add WHERE conditions based on context
        boolean hasWhere = false;
        
        if (context.containsKey("entity_name")) {
            sql.append(hasWhere ? " AND " : " WHERE ");
            sql.append("gc.company_name LIKE '%").append(context.get("entity_name")).append("%' ");
            hasWhere = true;
        }
        
        if (context.containsKey("currency")) {
            sql.append(hasWhere ? " AND " : " WHERE ");
            sql.append("wcf.currency = '").append(context.get("currency")).append("' ");
            hasWhere = true;
        }
        
        if (context.containsKey("has_amount_filter")) {
            sql.append(hasWhere ? " AND " : " WHERE ");
            String operator = (String) context.get("amount_operator");
            sql.append("wcf.limit_amount ").append(operator).append(" 1000000 ");
            hasWhere = true;
        }
        
        sql.append(" ORDER BY wcf.limit_amount DESC LIMIT 50");
        
        return sql.toString();
    }
    
    /**
     * Process query asynchronously for better performance
     */
    public CompletableFuture<QueryResult> processQueryAsync(String naturalLanguageQuery) {
        return CompletableFuture.supplyAsync(() -> processQuery(naturalLanguageQuery));
    }
    
    /**
     * Query Result class
     */
    public static class QueryResult {
        private String originalQuery;
        private String normalizedQuery;
        private String generatedSQL;
        private BusinessContext.QueryIntent queryIntent;
        private Map<String, Object> contextData;
        private long processingTimeMs;
        
        // Getters and Setters
        public String getOriginalQuery() { return originalQuery; }
        public void setOriginalQuery(String originalQuery) { this.originalQuery = originalQuery; }
        
        public String getNormalizedQuery() { return normalizedQuery; }
        public void setNormalizedQuery(String normalizedQuery) { this.normalizedQuery = normalizedQuery; }
        
        public String getGeneratedSQL() { return generatedSQL; }
        public void setGeneratedSQL(String generatedSQL) { this.generatedSQL = generatedSQL; }
        
        public BusinessContext.QueryIntent getQueryIntent() { return queryIntent; }
        public void setQueryIntent(BusinessContext.QueryIntent queryIntent) { this.queryIntent = queryIntent; }
        
        public Map<String, Object> getContextData() { return contextData; }
        public void setContextData(Map<String, Object> contextData) { this.contextData = contextData; }
        
        public long getProcessingTimeMs() { return processingTimeMs; }
        public void setProcessingTimeMs(long processingTimeMs) { this.processingTimeMs = processingTimeMs; }
    }
}