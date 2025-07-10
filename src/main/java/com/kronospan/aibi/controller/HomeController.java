package com.kronospan.aibi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Home Controller - Landing page for the Kronospan AI BI Platform
 */
@Controller
public class HomeController {
    
    /**
     * Home page with API documentation
     * GET /
     */
    @GetMapping("/")
    @ResponseBody
    public String home() {
        StringBuilder html = new StringBuilder();
        html.append("<!DOCTYPE html>");
        html.append("<html lang='en'>");
        html.append("<head>");
        html.append("<meta charset='UTF-8'>");
        html.append("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        html.append("<title>Kronospan AI BI Platform</title>");
        html.append("<style>");
        html.append("body { font-family: Arial, sans-serif; margin: 0; padding: 20px; background-color: #f5f5f5; }");
        html.append(".container { max-width: 1200px; margin: 0 auto; background: white; padding: 30px; border-radius: 10px; box-shadow: 0 2px 10px rgba(0,0,0,0.1); }");
        html.append("h1 { color: #2c3e50; text-align: center; margin-bottom: 30px; }");
        html.append("h2 { color: #34495e; border-bottom: 2px solid #3498db; padding-bottom: 10px; }");
        html.append(".endpoint { background: #ecf0f1; padding: 15px; margin: 10px 0; border-radius: 5px; border-left: 4px solid #3498db; }");
        html.append(".method { background: #3498db; color: white; padding: 5px 10px; border-radius: 3px; font-weight: bold; margin-right: 10px; }");
        html.append(".get { background: #27ae60; }");
        html.append(".post { background: #e74c3c; }");
        html.append(".description { margin-top: 10px; color: #7f8c8d; }");
        html.append(".status { text-align: center; padding: 20px; background: #d5f4e6; border-radius: 5px; margin-bottom: 20px; }");
        html.append("</style>");
        html.append("</head>");
        html.append("<body>");
        
        html.append("<div class='container'>");
        html.append("<h1>🏢 Kronospan AI Business Intelligence Platform</h1>");
        
        html.append("<div class='status'>");
        html.append("<h3>✅ System Status: ACTIVE</h3>");
        html.append("<p>Context Engineering System | Natural Language to SQL Conversion | Real Kronospan Data</p>");
        html.append("</div>");
        
        // Context Engineering API
        html.append("<h2>🧠 Context Engineering API</h2>");
        
        html.append("<div class='endpoint'>");
        html.append("<span class='method post'>POST</span>");
        html.append("<code>/api/v1/query/process</code>");
        html.append("<div class='description'>Process natural language queries and convert to SQL</div>");
        html.append("</div>");
        
        html.append("<div class='endpoint'>");
        html.append("<span class='method get'>GET</span>");
        html.append("<code>/api/v1/query/health</code>");
        html.append("<div class='description'>Check system health and status</div>");
        html.append("</div>");
        
        html.append("<div class='endpoint'>");
        html.append("<span class='method get'>GET</span>");
        html.append("<code>/api/v1/query/business-terms</code>");
        html.append("<div class='description'>Get available business terms and sample queries</div>");
        html.append("</div>");
        
        html.append("<div class='endpoint'>");
        html.append("<span class='method get'>GET</span>");
        html.append("<code>/api/v1/query/demo/{1-5}</code>");
        html.append("<div class='description'>Execute demo scenarios for C-level presentation</div>");
        html.append("</div>");
        
        // Data Import API
        html.append("<h2>📊 Data Import API</h2>");
        
        html.append("<div class='endpoint'>");
        html.append("<span class='method post'>POST</span>");
        html.append("<code>/api/v1/import/all</code>");
        html.append("<div class='description'>Import all Kronospan data (WCR + LTL Excel files)</div>");
        html.append("</div>");
        
        html.append("<div class='endpoint'>");
        html.append("<span class='method post'>POST</span>");
        html.append("<code>/api/v1/import/wcr</code>");
        html.append("<div class='description'>Import Working Capital Reports data only</div>");
        html.append("</div>");
        
        html.append("<div class='endpoint'>");
        html.append("<span class='method post'>POST</span>");
        html.append("<code>/api/v1/import/ltl</code>");
        html.append("<div class='description'>Import Long Term Loans data only</div>");
        html.append("</div>");
        
        html.append("<div class='endpoint'>");
        html.append("<span class='method get'>GET</span>");
        html.append("<code>/api/v1/import/status</code>");
        html.append("<div class='description'>Check import status and available datasets</div>");
        html.append("</div>");
        
        // Data Query API
        html.append("<h2>🔍 Data Query API</h2>");
        
        html.append("<div class='endpoint'>");
        html.append("<span class='method get'>GET</span>");
        html.append("<code>/api/v1/data/summary</code>");
        html.append("<div class='description'>Get data summary and statistics</div>");
        html.append("</div>");
        
        html.append("<div class='endpoint'>");
        html.append("<span class='method get'>GET</span>");
        html.append("<code>/api/v1/data/wcr</code>");
        html.append("<div class='description'>Get all working capital facilities</div>");
        html.append("</div>");
        
        html.append("<div class='endpoint'>");
        html.append("<span class='method get'>GET</span>");
        html.append("<code>/api/v1/data/ltl</code>");
        html.append("<div class='description'>Get all long term loans</div>");
        html.append("</div>");
        
        html.append("<div class='endpoint'>");
        html.append("<span class='method get'>GET</span>");
        html.append("<code>/api/v1/data/companies</code>");
        html.append("<div class='description'>Get all group companies</div>");
        html.append("</div>");
        
        html.append("<div class='endpoint'>");
        html.append("<span class='method get'>GET</span>");
        html.append("<code>/api/v1/data/demo/query/{1-5}</code>");
        html.append("<div class='description'>Execute predefined demo queries on real data</div>");
        html.append("</div>");
        
        // Database Console
        html.append("<h2>🗄️ Database Console</h2>");
        
        html.append("<div class='endpoint'>");
        html.append("<span class='method get'>GET</span>");
        html.append("<code>/h2-console</code>");
        html.append("<div class='description'>H2 Database Console (JDBC URL: jdbc:h2:mem:kronospandb)</div>");
        html.append("</div>");
        
        // Sample Commands
        html.append("<h2>🚀 Quick Start Commands</h2>");
        html.append("<div class='endpoint'>");
        html.append("<strong>1. Import Data:</strong> <code>POST /api/v1/import/all</code><br>");
        html.append("<strong>2. Check Status:</strong> <code>GET /api/v1/data/summary</code><br>");
        html.append("<strong>3. Run Demo:</strong> <code>GET /api/v1/data/demo/query/1</code><br>");
        html.append("<strong>4. Natural Language Query:</strong> <code>POST /api/v1/query/process</code> with JSON body");
        html.append("</div>");
        
        html.append("<div style='text-align: center; margin-top: 30px; color: #7f8c8d;'>");
        html.append("<p>Built with Context Engineering principles for Kronospan Cyprus Demo</p>");
        html.append("<p>Spring Boot 2.7.18 | Java 8 | H2 Database | Apache POI</p>");
        html.append("</div>");
        
        html.append("</div>");
        html.append("</body>");
        html.append("</html>");
        
        return html.toString();
    }
    
    /**
     * API status endpoint
     * GET /api
     */
    @GetMapping("/api")
    @ResponseBody
    public Map<String, Object> apiStatus() {
        Map<String, Object> status = new HashMap<>();
        status.put("service", "Kronospan AI BI Platform");
        status.put("version", "1.0.0");
        status.put("status", "ACTIVE");
        status.put("context_engineering", "ENABLED");
        status.put("database", "H2 In-Memory");
        status.put("endpoints", new String[]{
            "/api/v1/query/*",
            "/api/v1/import/*", 
            "/api/v1/data/*",
            "/h2-console"
        });
        return status;
    }
}