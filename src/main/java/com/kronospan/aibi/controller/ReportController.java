package com.kronospan.aibi.controller;

import com.kronospan.aibi.service.ReportGenerationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Report Generation Controller
 * Handles executive report generation and multi-page layout
 */
@RestController
@RequestMapping("/api/v1/reports")
@CrossOrigin(origins = "*")
public class ReportController {
    
    @Autowired
    private ReportGenerationService reportService;
    
    /**
     * Generate Working Capital Analysis Report
     * GET /api/v1/reports/working-capital
     */
    @GetMapping("/working-capital")
    public ResponseEntity<ReportGenerationService.ExecutiveReport> generateWorkingCapitalReport(
            @RequestParam(required = false) String currency,
            @RequestParam(required = false) String country) {
        
        Map<String, Object> parameters = new HashMap<>();
        if (currency != null) parameters.put("currency", currency);
        if (country != null) parameters.put("country", country);
        
        ReportGenerationService.ExecutiveReport report = 
            reportService.generateExecutiveReport("working_capital_analysis", parameters);
        
        return ResponseEntity.ok(report);
    }
    
    /**
     * Generate Cyprus Entities Governance Report
     * GET /api/v1/reports/cyprus-entities
     */
    @GetMapping("/cyprus-entities")
    public ResponseEntity<ReportGenerationService.ExecutiveReport> generateCyprusEntitiesReport() {
        
        Map<String, Object> parameters = new HashMap<>();
        
        ReportGenerationService.ExecutiveReport report = 
            reportService.generateExecutiveReport("cyprus_entities_governance", parameters);
        
        return ResponseEntity.ok(report);
    }
    
    /**
     * Generate Financial Variance Analysis Report
     * GET /api/v1/reports/variance-analysis
     */
    @GetMapping("/variance-analysis")
    public ResponseEntity<ReportGenerationService.ExecutiveReport> generateVarianceReport(
            @RequestParam(required = false, defaultValue = "2023") String fromPeriod,
            @RequestParam(required = false, defaultValue = "2024") String toPeriod) {
        
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("from_period", fromPeriod);
        parameters.put("to_period", toPeriod);
        
        ReportGenerationService.ExecutiveReport report = 
            reportService.generateExecutiveReport("financial_variance_analysis", parameters);
        
        return ResponseEntity.ok(report);
    }
    
    /**
     * Generate Bank Exposure Analysis Report
     * GET /api/v1/reports/bank-exposure
     */
    @GetMapping("/bank-exposure")
    public ResponseEntity<ReportGenerationService.ExecutiveReport> generateBankExposureReport(
            @RequestParam(required = false) String bankName) {
        
        Map<String, Object> parameters = new HashMap<>();
        if (bankName != null) parameters.put("bank_name", bankName);
        
        ReportGenerationService.ExecutiveReport report = 
            reportService.generateExecutiveReport("bank_exposure_analysis", parameters);
        
        return ResponseEntity.ok(report);
    }
    
    /**
     * Generate Directorship Analysis Report
     * GET /api/v1/reports/directorship
     */
    @GetMapping("/directorship")
    public ResponseEntity<ReportGenerationService.ExecutiveReport> generateDirectorshipReport(
            @RequestParam(required = false, defaultValue = "Matthias Kaindl") String directorName) {
        
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("director_name", directorName);
        
        ReportGenerationService.ExecutiveReport report = 
            reportService.generateExecutiveReport("directorship_analysis", parameters);
        
        return ResponseEntity.ok(report);
    }
    
    /**
     * Generate Custom Report based on query
     * POST /api/v1/reports/custom
     */
    @PostMapping("/custom")
    public ResponseEntity<ReportGenerationService.ExecutiveReport> generateCustomReport(
            @RequestBody Map<String, Object> request) {
        
        String reportType = (String) request.getOrDefault("report_type", "standard");
        Map<String, Object> parameters = (Map<String, Object>) request.getOrDefault("parameters", new HashMap<>());
        
        ReportGenerationService.ExecutiveReport report = 
            reportService.generateExecutiveReport(reportType, parameters);
        
        return ResponseEntity.ok(report);
    }
    
    /**
     * Get available report types
     * GET /api/v1/reports/types
     */
    @GetMapping("/types")
    public ResponseEntity<Map<String, Object>> getAvailableReportTypes() {
        Map<String, Object> response = new HashMap<>();
        
        response.put("report_types", new String[]{
            "working_capital_analysis",
            "cyprus_entities_governance", 
            "financial_variance_analysis",
            "bank_exposure_analysis",
            "directorship_analysis"
        });
        
        Map<String, String> descriptions = new HashMap<>();
        descriptions.put("working_capital_analysis", "Comprehensive analysis of working capital facilities by currency, country, and bank");
        descriptions.put("cyprus_entities_governance", "Corporate governance report for all Cyprus-registered entities with director analysis");
        descriptions.put("financial_variance_analysis", "Period-over-period variance analysis of financial metrics and facility changes");
        descriptions.put("bank_exposure_analysis", "Banking partner exposure analysis with concentration risk assessment");
        descriptions.put("directorship_analysis", "Individual director appointment analysis and governance tracking");
        response.put("descriptions", descriptions);
        
        response.put("demo_scenarios", new String[]{
            "GET /api/v1/reports/working-capital - Working capital analysis for demo",
            "GET /api/v1/reports/cyprus-entities - Cyprus entities governance report",
            "GET /api/v1/reports/bank-exposure?bankName=PKO BP - PKO BP exposure analysis",
            "GET /api/v1/reports/directorship?directorName=Matthias Kaindl - Matthias Kaindl directorship report",
            "GET /api/v1/reports/variance-analysis - December 2023 vs July 2024 variance"
        });
        
        response.put("features", new String[]{
            "Multi-page layout: 1 description + 5 data columns per page",
            "Executive summary with key metrics and insights",
            "Professional formatting with status indicators",
            "Chart data for visualization integration",
            "Risk assessment and compliance tracking",
            "Variance analysis and trend identification"
        });
        
        return ResponseEntity.ok(response);
    }
    
    /**
     * Generate demo report for presentation
     * GET /api/v1/reports/demo/{scenario}
     */
    @GetMapping("/demo/{scenario}")
    public ResponseEntity<ReportGenerationService.ExecutiveReport> generateDemoReport(@PathVariable String scenario) {
        Map<String, Object> parameters = new HashMap<>();
        
        switch (scenario) {
            case "1":
                // PKO BP facilities over 1M EUR report
                parameters.put("bank_name", "PKO BP");
                parameters.put("min_amount", 1000000);
                return ResponseEntity.ok(reportService.generateExecutiveReport("bank_exposure_analysis", parameters));
            
            case "2":
                // Matthias Kaindl directorship report
                parameters.put("director_name", "Matthias Kaindl");
                return ResponseEntity.ok(reportService.generateExecutiveReport("directorship_analysis", parameters));
            
            case "3":
                // High utilization companies report
                parameters.put("min_utilization", 80);
                return ResponseEntity.ok(reportService.generateExecutiveReport("working_capital_analysis", parameters));
            
            case "4":
                // Poland vs Romania comparison report
                parameters.put("countries", new String[]{"Poland", "Romania"});
                return ResponseEntity.ok(reportService.generateExecutiveReport("financial_variance_analysis", parameters));
            
            case "5":
                // Cyprus entities report
                return ResponseEntity.ok(reportService.generateExecutiveReport("cyprus_entities_governance", parameters));
            
            case "6":
                // Variance analysis report
                parameters.put("from_period", "2023-12");
                parameters.put("to_period", "2024-07");
                return ResponseEntity.ok(reportService.generateExecutiveReport("financial_variance_analysis", parameters));
            
            default:
                return ResponseEntity.notFound().build();
        }
    }
}