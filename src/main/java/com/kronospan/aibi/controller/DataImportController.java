package com.kronospan.aibi.controller;

import com.kronospan.aibi.service.importer.ExcelImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * Data Import Controller
 * Handles import of actual Kronospan data from Excel/PDF files
 */
@RestController
@RequestMapping("/api/v1/import")
@CrossOrigin(origins = "*")
public class DataImportController {
    
    @Autowired
    private ExcelImportService excelImportService;
    
    /**
     * Import all Kronospan data
     * POST /api/v1/import/all
     */
    @PostMapping("/all")
    public ResponseEntity<Map<String, Object>> importAllData() {
        Map<String, Object> response = new HashMap<>();
        
        try {
            long startTime = System.currentTimeMillis();
            
            // Import WCR data
            excelImportService.importWCRData();
            
            // Import LTL data
            excelImportService.importLTLData();
            
            long endTime = System.currentTimeMillis();
            
            response.put("status", "SUCCESS");
            response.put("message", "All Kronospan data imported successfully");
            response.put("processing_time_ms", endTime - startTime);
            response.put("imported_datasets", new String[]{"WCR_2024", "WCR_2023", "LTL_Data"});
            
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            response.put("status", "ERROR");
            response.put("error", e.getMessage());
            return ResponseEntity.internalServerError().body(response);
        }
    }
    
    /**
     * Import only WCR data
     * POST /api/v1/import/wcr
     */
    @PostMapping("/wcr")
    public ResponseEntity<Map<String, Object>> importWCRData() {
        Map<String, Object> response = new HashMap<>();
        
        try {
            long startTime = System.currentTimeMillis();
            
            excelImportService.importWCRData();
            
            long endTime = System.currentTimeMillis();
            
            response.put("status", "SUCCESS");
            response.put("message", "WCR data imported successfully");
            response.put("processing_time_ms", endTime - startTime);
            response.put("files_processed", new String[]{"WCR_16_07_2024.xlsx", "WCR_27_12_2023.xlsx"});
            
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            response.put("status", "ERROR");
            response.put("error", e.getMessage());
            return ResponseEntity.internalServerError().body(response);
        }
    }
    
    /**
     * Import only LTL data
     * POST /api/v1/import/ltl
     */
    @PostMapping("/ltl")
    public ResponseEntity<Map<String, Object>> importLTLData() {
        Map<String, Object> response = new HashMap<>();
        
        try {
            long startTime = System.currentTimeMillis();
            
            excelImportService.importLTLData();
            
            long endTime = System.currentTimeMillis();
            
            response.put("status", "SUCCESS");
            response.put("message", "LTL data imported successfully");
            response.put("processing_time_ms", endTime - startTime);
            response.put("files_processed", new String[]{"LTL_Data.xlsx"});
            
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            response.put("status", "ERROR");
            response.put("error", e.getMessage());
            return ResponseEntity.internalServerError().body(response);
        }
    }
    
    /**
     * Import data asynchronously
     * POST /api/v1/import/async
     */
    @PostMapping("/async")
    public CompletableFuture<ResponseEntity<Map<String, Object>>> importDataAsync() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                long startTime = System.currentTimeMillis();
                
                excelImportService.importWCRData();
                excelImportService.importLTLData();
                
                long endTime = System.currentTimeMillis();
                
                Map<String, Object> response = new HashMap<>();
                response.put("status", "SUCCESS");
                response.put("message", "All data imported successfully (async)");
                response.put("processing_time_ms", endTime - startTime);
                
                return ResponseEntity.ok(response);
                
            } catch (Exception e) {
                Map<String, Object> response = new HashMap<>();
                response.put("status", "ERROR");
                response.put("error", e.getMessage());
                return ResponseEntity.internalServerError().body(response);
            }
        });
    }
    
    /**
     * Get import status
     * GET /api/v1/import/status
     */
    @GetMapping("/status")
    public ResponseEntity<Map<String, Object>> getImportStatus() {
        Map<String, Object> status = new HashMap<>();
        
        status.put("available_datasets", new String[]{
            "WCR_16_07_2024.xlsx - Working Capital Report July 2024",
            "WCR_27_12_2023.xlsx - Working Capital Report December 2023", 
            "LTL_Data.xlsx - Long Term Loans Data"
        });
        
        status.put("available_endpoints", new String[]{
            "POST /api/v1/import/all - Import all data",
            "POST /api/v1/import/wcr - Import WCR data only",
            "POST /api/v1/import/ltl - Import LTL data only",
            "POST /api/v1/import/async - Import all data asynchronously"
        });
        
        status.put("data_location", new String[]{
            "Demo_data_1/Demo_data_1/ - WCR files and financial PDFs",
            "Demo_data_2/Demo_data_2/ - LTL files and Cyprus reports"
        });
        
        return ResponseEntity.ok(status);
    }
}