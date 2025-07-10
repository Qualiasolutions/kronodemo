package com.kronospan.aibi.controller;

import com.kronospan.aibi.context.QueryProcessor;
import com.kronospan.aibi.context.BusinessContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * REST API Controller for Context Engineering Query Processing
 */
@RestController
@RequestMapping("/api/v1/query")
@CrossOrigin(origins = "*")
public class QueryController {
    
    @Autowired
    private QueryProcessor queryProcessor;
    
    @Autowired
    private BusinessContext businessContext;
    
    /**
     * Process a natural language query
     * 
     * POST /api/v1/query/process
     * Body: {"query": "Show me all facilities with limits over 1 million EUR"}
     */
    @PostMapping("/process")
    public ResponseEntity<QueryProcessor.QueryResult> processQuery(@RequestBody Map<String, String> request) {
        String query = request.get("query");
        
        if (query == null || query.trim().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        
        try {
            QueryProcessor.QueryResult result = queryProcessor.processQuery(query);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
    
    /**
     * Process query asynchronously
     * 
     * POST /api/v1/query/process-async
     */
    @PostMapping("/process-async")
    public CompletableFuture<ResponseEntity<QueryProcessor.QueryResult>> processQueryAsync(@RequestBody Map<String, String> request) {
        String query = request.get("query");
        
        if (query == null || query.trim().isEmpty()) {
            return CompletableFuture.completedFuture(ResponseEntity.badRequest().build());
        }
        
        return queryProcessor.processQueryAsync(query)
                .thenApply(result -> ResponseEntity.ok(result))
                .exceptionally(throwable -> ResponseEntity.internalServerError().build());
    }
    
    /**
     * Get available business terms
     * 
     * GET /api/v1/query/business-terms
     */
    @GetMapping("/business-terms")
    public ResponseEntity<Map<String, Object>> getBusinessTerms() {
        Map<String, Object> response = new HashMap<>();
        
        response.put("sample_terms", new String[]{
            "WCR - Working Capital Report",
            "LTL - Long Term Loans", 
            "RC - Revolving Credit",
            "PKO BP - Powszechna Kasa Oszczędności Bank Polski",
            "facility - credit facility or loan arrangement",
            "utilization - amount used divided by total available"
        });
        
        response.put("sample_queries", new String[]{
            "Show me all facilities with limits over 1 million EUR",
            "What are the working capital facilities of Kronospan Asia as at 2023?",
            "Which companies have utilization over 80%?",
            "Compare loan amounts between Oxnard and Spanaco",
            "Generate a report on PKO BP facilities"
        });
        
        response.put("supported_entities", new String[]{
            "Companies: Kronospan Asia, Oxnard, Spanaco",
            "Banks: PKO BP, Erste, Raiffeisen",
            "Countries: Poland, Romania, Cyprus",
            "Currencies: EUR, PLN, BGN"
        });
        
        return ResponseEntity.ok(response);
    }
    
    /**
     * Health check endpoint
     * 
     * GET /api/v1/query/health
     */
    @GetMapping("/health")
    public ResponseEntity<Map<String, Object>> healthCheck() {
        Map<String, Object> health = new HashMap<>();
        health.put("status", "UP");
        health.put("service", "Kronospan AI BI Platform");
        health.put("version", "1.0.0");
        health.put("context_engine", "ACTIVE");
        health.put("query_processor", "READY");
        health.put("timestamp", System.currentTimeMillis());
        
        return ResponseEntity.ok(health);
    }
    
    /**
     * Test endpoint for demo scenarios
     * 
     * GET /api/v1/query/demo/{scenario}
     */
    @GetMapping("/demo/{scenario}")
    public ResponseEntity<QueryProcessor.QueryResult> demoScenario(@PathVariable String scenario) {
        String query;
        
        switch (scenario) {
            case "1":
                query = "Show me all working capital facilities with limits over 1 million EUR";
                break;
            case "2":
                query = "What are the total facilities of Kronospan Asia as at 2023?";
                break;
            case "3":
                query = "Which companies have utilization over 80%?";
                break;
            case "4":
                query = "Compare facility amounts between different banks";
                break;
            case "5":
                query = "Generate a report on PKO BP facilities";
                break;
            default:
                return ResponseEntity.notFound().build();
        }
        
        QueryProcessor.QueryResult result = queryProcessor.processQuery(query);
        return ResponseEntity.ok(result);
    }
}