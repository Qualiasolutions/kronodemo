package com.kronospan.aibi.service;

import com.kronospan.aibi.context.QueryProcessor;
import com.kronospan.aibi.repository.WorkingCapitalFacilityRepository;
import com.kronospan.aibi.repository.LongTermLoanRepository;
import com.kronospan.aibi.repository.DirectorRepository;
import com.kronospan.aibi.repository.GroupCompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Multi-Page Report Generation Engine
 * 
 * Generates professional executive reports with:
 * - 1 description column + 5 data columns per page
 * - Multi-page layout for 16-20 total columns
 * - Executive summary and financial analysis
 * - Charts and visualizations data
 */
@Service
public class ReportGenerationService {
    
    @Autowired
    private QueryProcessor queryProcessor;
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Autowired
    private WorkingCapitalFacilityRepository wcrRepository;
    
    @Autowired
    private LongTermLoanRepository ltlRepository;
    
    @Autowired
    private DirectorRepository directorRepository;
    
    @Autowired
    private GroupCompanyRepository companyRepository;
    
    /**
     * Generate comprehensive executive report
     */
    public ExecutiveReport generateExecutiveReport(String reportType, Map<String, Object> parameters) {
        ExecutiveReport report = new ExecutiveReport();
        report.setReportTitle(getReportTitle(reportType));
        report.setGeneratedAt(LocalDateTime.now());
        report.setReportType(reportType);
        
        switch (reportType.toLowerCase()) {
            case "working_capital_analysis":
                return generateWorkingCapitalReport(parameters);
            case "cyprus_entities_governance":
                return generateCyprusEntitiesReport(parameters);
            case "financial_variance_analysis":
                return generateVarianceReport(parameters);
            case "bank_exposure_analysis":
                return generateBankExposureReport(parameters);
            case "directorship_analysis":
                return generateDirectorshipReport(parameters);
            default:
                return generateStandardReport(parameters);
        }
    }
    
    /**
     * Generate Working Capital Analysis Report
     */
    private ExecutiveReport generateWorkingCapitalReport(Map<String, Object> parameters) {
        ExecutiveReport report = new ExecutiveReport();
        report.setReportTitle("Working Capital Facilities Analysis");
        report.setGeneratedAt(LocalDateTime.now());
        
        // Executive Summary
        Map<String, Object> summary = generateWorkingCapitalSummary();
        report.setExecutiveSummary(summary);
        
        // Page 1: Overview and Key Metrics
        ReportPage page1 = new ReportPage();
        page1.setPageNumber(1);
        page1.setPageTitle("Working Capital Overview");
        page1.addColumn("Description", Arrays.asList(
            "Total Available Facilities", 
            "Total Utilized Amount", 
            "Average Utilization Rate", 
            "Number of Banking Partners",
            "Highest Single Facility"
        ));
        page1.addColumn("EUR Amount", Arrays.asList(
            formatCurrency(getTotalAvailableFacilities("EUR")),
            formatCurrency(getTotalUtilizedAmount("EUR")),
            formatPercentage(getAverageUtilization("EUR")),
            getBankingPartnerCount("EUR").toString(),
            formatCurrency(getHighestFacility("EUR"))
        ));
        page1.addColumn("PLN Amount", Arrays.asList(
            formatCurrency(getTotalAvailableFacilities("PLN")),
            formatCurrency(getTotalUtilizedAmount("PLN")),
            formatPercentage(getAverageUtilization("PLN")),
            getBankingPartnerCount("PLN").toString(),
            formatCurrency(getHighestFacility("PLN"))
        ));
        page1.addColumn("BGN Amount", Arrays.asList(
            formatCurrency(getTotalAvailableFacilities("BGN")),
            formatCurrency(getTotalUtilizedAmount("BGN")),
            formatPercentage(getAverageUtilization("BGN")),
            getBankingPartnerCount("BGN").toString(),
            formatCurrency(getHighestFacility("BGN"))
        ));
        page1.addColumn("Variance %", Arrays.asList(
            calculateVariance("available", "2024", "2023"),
            calculateVariance("utilized", "2024", "2023"),
            calculateVariance("utilization", "2024", "2023"),
            calculateVariance("banks", "2024", "2023"),
            calculateVariance("max_facility", "2024", "2023")
        ));
        page1.addColumn("Risk Level", Arrays.asList(
            assessRiskLevel("liquidity"),
            assessRiskLevel("utilization"),
            assessRiskLevel("concentration"),
            assessRiskLevel("diversification"),
            assessRiskLevel("exposure")
        ));
        
        report.addPage(page1);
        
        // Page 2: Bank-wise Analysis
        ReportPage page2 = new ReportPage();
        page2.setPageNumber(2);
        page2.setPageTitle("Banking Partner Analysis");
        
        List<Map<String, Object>> bankData = getBankWiseAnalysis();
        List<String> bankNames = new ArrayList<>();
        List<String> totalLimits = new ArrayList<>();
        List<String> utilizations = new ArrayList<>();
        List<String> currencies = new ArrayList<>();
        List<String> riskRatings = new ArrayList<>();
        List<String> recommendations = new ArrayList<>();
        
        for (Map<String, Object> bank : bankData) {
            bankNames.add((String) bank.get("bank_name"));
            totalLimits.add(formatCurrency((BigDecimal) bank.get("total_limit")));
            utilizations.add(formatPercentage((BigDecimal) bank.get("utilization_rate")));
            currencies.add((String) bank.get("primary_currency"));
            riskRatings.add(assessBankRisk((String) bank.get("bank_name")));
            recommendations.add(getBankRecommendation((String) bank.get("bank_name")));
        }
        
        page2.addColumn("Banking Partner", bankNames);
        page2.addColumn("Total Limit", totalLimits);
        page2.addColumn("Utilization %", utilizations);
        page2.addColumn("Primary Currency", currencies);
        page2.addColumn("Risk Rating", riskRatings);
        page2.addColumn("Recommendation", recommendations);
        
        report.addPage(page2);
        
        // Page 3: Country-wise Analysis
        ReportPage page3 = new ReportPage();
        page3.setPageNumber(3);
        page3.setPageTitle("Geographical Distribution Analysis");
        
        List<Map<String, Object>> countryData = getCountryWiseAnalysis();
        List<String> countries = new ArrayList<>();
        List<String> totalFacilities = new ArrayList<>();
        List<String> avgUtilization = new ArrayList<>();
        List<String> riskScore = new ArrayList<>();
        List<String> concentration = new ArrayList<>();
        List<String> growth = new ArrayList<>();
        
        for (Map<String, Object> country : countryData) {
            countries.add((String) country.get("country"));
            totalFacilities.add(formatCurrency((BigDecimal) country.get("total_facilities")));
            avgUtilization.add(formatPercentage((BigDecimal) country.get("avg_utilization")));
            riskScore.add(assessCountryRisk((String) country.get("country")));
            concentration.add(formatPercentage((BigDecimal) country.get("concentration")));
            growth.add(formatPercentage((BigDecimal) country.get("growth_rate")));
        }
        
        page3.addColumn("Country", countries);
        page3.addColumn("Total Facilities", totalFacilities);
        page3.addColumn("Avg Utilization", avgUtilization);
        page3.addColumn("Risk Score", riskScore);
        page3.addColumn("Concentration", concentration);
        page3.addColumn("YoY Growth", growth);
        
        report.addPage(page3);
        
        // Add chart data for visualizations
        report.setChartData(generateWorkingCapitalCharts());
        
        return report;
    }
    
    /**
     * Generate Cyprus Entities Governance Report
     */
    private ExecutiveReport generateCyprusEntitiesReport(Map<String, Object> parameters) {
        ExecutiveReport report = new ExecutiveReport();
        report.setReportTitle("Cyprus Entities Corporate Governance Report");
        report.setGeneratedAt(LocalDateTime.now());
        
        // Page 1: Entity Overview
        ReportPage page1 = new ReportPage();
        page1.setPageNumber(1);
        page1.setPageTitle("Cyprus Group Entities Overview");
        
        List<Map<String, Object>> entities = getCyprusEntities();
        List<String> entityNames = new ArrayList<>();
        List<String> incorporationDates = new ArrayList<>();
        List<String> shareCapital = new ArrayList<>();
        List<String> directorCount = new ArrayList<>();
        List<String> status = new ArrayList<>();
        List<String> compliance = new ArrayList<>();
        
        for (Map<String, Object> entity : entities) {
            entityNames.add((String) entity.get("company_name"));
            incorporationDates.add(formatDate(entity.get("incorporation_date")));
            shareCapital.add(formatCurrency((BigDecimal) entity.get("share_capital")));
            directorCount.add(entity.get("director_count").toString());
            status.add((String) entity.get("status"));
            compliance.add(assessComplianceStatus((String) entity.get("company_name")));
        }
        
        page1.addColumn("Entity Name", entityNames);
        page1.addColumn("Incorporation", incorporationDates);
        page1.addColumn("Share Capital", shareCapital);
        page1.addColumn("Directors", directorCount);
        page1.addColumn("Status", status);
        page1.addColumn("Compliance", compliance);
        
        report.addPage(page1);
        
        // Page 2: Director Analysis
        ReportPage page2 = new ReportPage();
        page2.setPageNumber(2);
        page2.setPageTitle("Directorship Analysis");
        
        List<Map<String, Object>> directors = getDirectorshipAnalysis();
        List<String> directorNames = new ArrayList<>();
        List<String> companies = new ArrayList<>();
        List<String> appointments = new ArrayList<>();
        List<String> positions = new ArrayList<>();
        List<String> tenure = new ArrayList<>();
        List<String> riskFlags = new ArrayList<>();
        
        for (Map<String, Object> director : directors) {
            directorNames.add((String) director.get("director_name"));
            companies.add((String) director.get("company_name"));
            appointments.add(formatDate(director.get("appointment_date")));
            positions.add((String) director.get("position"));
            tenure.add(calculateTenure(director.get("appointment_date")));
            riskFlags.add(assessDirectorRisk((String) director.get("director_name")));
        }
        
        page2.addColumn("Director Name", directorNames);
        page2.addColumn("Company", companies);
        page2.addColumn("Appointment", appointments);
        page2.addColumn("Position", positions);
        page2.addColumn("Tenure", tenure);
        page2.addColumn("Risk Flags", riskFlags);
        
        report.addPage(page2);
        
        return report;
    }
    
    /**
     * Generate Bank Exposure Analysis Report
     */
    private ExecutiveReport generateBankExposureReport(Map<String, Object> parameters) {
        ExecutiveReport report = new ExecutiveReport();
        report.setReportTitle("Banking Partner Exposure Analysis");
        report.setGeneratedAt(LocalDateTime.now());
        
        // Page 1: PKO BP Analysis (Demo Focus)
        ReportPage page1 = new ReportPage();
        page1.setPageNumber(1);
        page1.setPageTitle("PKO BP Exposure Analysis");
        
        page1.addColumn("Metric", Arrays.asList(
            "Total Credit Facilities",
            "Facilities > 1M EUR",
            "Average Facility Size", 
            "Total Exposure",
            "Utilization Rate",
            "Risk Concentration"
        ));
        page1.addColumn("Current Value", Arrays.asList(
            getPKOFacilityCount().toString(),
            getPKOLargeFacilities().toString(),
            formatCurrency(getPKOAverageFacility()),
            formatCurrency(getPKOTotalExposure()),
            formatPercentage(getPKOUtilization()),
            formatPercentage(getPKOConcentration())
        ));
        page1.addColumn("Benchmark", Arrays.asList(
            "15-25 facilities",
            "< 5 large facilities",
            "500K - 2M EUR",
            "< 50M EUR total",
            "60-80%",
            "< 30%"
        ));
        page1.addColumn("Status", Arrays.asList(
            assessMetric(getPKOFacilityCount(), 15, 25),
            assessMetric(getPKOLargeFacilities(), 0, 5),
            assessCurrencyMetric(getPKOAverageFacility(), 500000, 2000000),
            assessCurrencyMetric(getPKOTotalExposure(), 0, 50000000),
            assessPercentageMetric(getPKOUtilization(), 60, 80),
            assessPercentageMetric(getPKOConcentration(), 0, 30)
        ));
        page1.addColumn("Trend", Arrays.asList(
            "↗ Increasing",
            "→ Stable", 
            "↗ Growing",
            "↗ Rising",
            "→ Stable",
            "⚠ Watch"
        ));
        page1.addColumn("Action Required", Arrays.asList(
            "Monitor growth",
            "Review large facilities",
            "Optimize facility sizes",
            "Consider diversification",
            "Maintain current levels",
            "Reduce concentration"
        ));
        
        report.addPage(page1);
        
        return report;
    }
    
    /**
     * Generate Financial Variance Report
     */
    private ExecutiveReport generateVarianceReport(Map<String, Object> parameters) {
        ExecutiveReport report = new ExecutiveReport();
        report.setReportTitle("Financial Variance Analysis: Dec 2023 vs Jul 2024");
        report.setGeneratedAt(LocalDateTime.now());
        
        // Page 1: Working Capital Variance
        ReportPage page1 = new ReportPage();
        page1.setPageNumber(1);
        page1.setPageTitle("Working Capital Facility Changes");
        
        List<Map<String, Object>> variance = getWCRVarianceData();
        List<String> companies = new ArrayList<>();
        List<String> dec2023 = new ArrayList<>();
        List<String> jul2024 = new ArrayList<>();
        List<String> varianceAmt = new ArrayList<>();
        List<String> variancePct = new ArrayList<>();
        List<String> analysis = new ArrayList<>();
        
        for (Map<String, Object> row : variance) {
            companies.add((String) row.get("company_name"));
            dec2023.add(formatCurrency((BigDecimal) row.get("amount_2023")));
            jul2024.add(formatCurrency((BigDecimal) row.get("amount_2024")));
            varianceAmt.add(formatCurrency((BigDecimal) row.get("variance")));
            variancePct.add(formatPercentage(calculatePercentageVariance(
                (BigDecimal) row.get("amount_2023"), 
                (BigDecimal) row.get("amount_2024")
            )));
            analysis.add(analyzeVariance((BigDecimal) row.get("variance")));
        }
        
        page1.addColumn("Company", companies);
        page1.addColumn("Dec 2023", dec2023);
        page1.addColumn("Jul 2024", jul2024);
        page1.addColumn("Variance", varianceAmt);
        page1.addColumn("Change %", variancePct);
        page1.addColumn("Analysis", analysis);
        
        report.addPage(page1);
        
        return report;
    }
    
    /**
     * Generate Directorship Report for specific person (e.g., Matthias Kaindl)
     */
    private ExecutiveReport generateDirectorshipReport(Map<String, Object> parameters) {
        ExecutiveReport report = new ExecutiveReport();
        String directorName = (String) parameters.getOrDefault("director_name", "Matthias Kaindl");
        report.setReportTitle("Directorship Analysis: " + directorName);
        report.setGeneratedAt(LocalDateTime.now());
        
        // Page 1: Director Appointments
        ReportPage page1 = new ReportPage();
        page1.setPageNumber(1);
        page1.setPageTitle("Current and Historical Appointments");
        
        List<Map<String, Object>> appointments = getDirectorAppointments(directorName);
        List<String> companies = new ArrayList<>();
        List<String> positions = new ArrayList<>();
        List<String> appointmentDates = new ArrayList<>();
        List<String> jurisdictions = new ArrayList<>();
        List<String> status = new ArrayList<>();
        List<String> signingPowers = new ArrayList<>();
        
        for (Map<String, Object> appointment : appointments) {
            companies.add((String) appointment.get("company_name"));
            positions.add((String) appointment.get("position"));
            appointmentDates.add(formatDate(appointment.get("appointment_date")));
            jurisdictions.add((String) appointment.get("jurisdiction"));
            status.add((String) appointment.get("status"));
            signingPowers.add((String) appointment.get("signing_powers"));
        }
        
        page1.addColumn("Company", companies);
        page1.addColumn("Position", positions);
        page1.addColumn("Appointment", appointmentDates);
        page1.addColumn("Jurisdiction", jurisdictions);
        page1.addColumn("Status", status);
        page1.addColumn("Signing Powers", signingPowers);
        
        report.addPage(page1);
        
        return report;
    }
    
    /**
     * Generate Standard Multi-Column Report
     */
    private ExecutiveReport generateStandardReport(Map<String, Object> parameters) {
        ExecutiveReport report = new ExecutiveReport();
        report.setReportTitle("Standard Executive Report");
        report.setGeneratedAt(LocalDateTime.now());
        
        ReportPage page1 = new ReportPage();
        page1.setPageNumber(1);
        page1.setPageTitle("Executive Summary");
        
        page1.addColumn("Metric", Arrays.asList("Sample Metric 1", "Sample Metric 2", "Sample Metric 3"));
        page1.addColumn("Value", Arrays.asList("Value 1", "Value 2", "Value 3"));
        page1.addColumn("Target", Arrays.asList("Target 1", "Target 2", "Target 3"));
        page1.addColumn("Status", Arrays.asList("On Track", "At Risk", "Achieved"));
        page1.addColumn("Trend", Arrays.asList("↗", "↘", "→"));
        page1.addColumn("Action", Arrays.asList("Monitor", "Improve", "Maintain"));
        
        report.addPage(page1);
        return report;
    }
    
    // Helper methods for data retrieval and calculations
    
    private BigDecimal getTotalAvailableFacilities(String currency) {
        try {
            return jdbcTemplate.queryForObject(
                "SELECT COALESCE(SUM(limit_amount), 0) FROM working_capital_facilities WHERE currency = ?", 
                BigDecimal.class, currency);
        } catch (Exception e) {
            return BigDecimal.ZERO;
        }
    }
    
    private BigDecimal getTotalUtilizedAmount(String currency) {
        try {
            return jdbcTemplate.queryForObject(
                "SELECT COALESCE(SUM(utilized_amount), 0) FROM working_capital_facilities WHERE currency = ?", 
                BigDecimal.class, currency);
        } catch (Exception e) {
            return BigDecimal.ZERO;
        }
    }
    
    private BigDecimal getAverageUtilization(String currency) {
        try {
            return jdbcTemplate.queryForObject(
                "SELECT COALESCE(AVG(utilization_percentage), 0) FROM working_capital_facilities WHERE currency = ?", 
                BigDecimal.class, currency);
        } catch (Exception e) {
            return BigDecimal.ZERO;
        }
    }
    
    private Integer getBankingPartnerCount(String currency) {
        try {
            return jdbcTemplate.queryForObject(
                "SELECT COUNT(DISTINCT bank_name) FROM working_capital_facilities WHERE currency = ?", 
                Integer.class, currency);
        } catch (Exception e) {
            return 0;
        }
    }
    
    private BigDecimal getHighestFacility(String currency) {
        try {
            return jdbcTemplate.queryForObject(
                "SELECT COALESCE(MAX(limit_amount), 0) FROM working_capital_facilities WHERE currency = ?", 
                BigDecimal.class, currency);
        } catch (Exception e) {
            return BigDecimal.ZERO;
        }
    }
    
    private List<Map<String, Object>> getBankWiseAnalysis() {
        try {
            return jdbcTemplate.queryForList(
                "SELECT bank_name, SUM(limit_amount) as total_limit, AVG(utilization_percentage) as utilization_rate, " +
                "currency as primary_currency FROM working_capital_facilities " +
                "GROUP BY bank_name, currency ORDER BY total_limit DESC LIMIT 10");
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
    
    private List<Map<String, Object>> getCountryWiseAnalysis() {
        try {
            return jdbcTemplate.queryForList(
                "SELECT gc.country, SUM(wcf.limit_amount) as total_facilities, " +
                "AVG(wcf.utilization_percentage) as avg_utilization, " +
                "COUNT(*) as facility_count " +
                "FROM working_capital_facilities wcf " +
                "JOIN group_companies gc ON wcf.company_id = gc.id " +
                "GROUP BY gc.country ORDER BY total_facilities DESC");
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
    
    private List<Map<String, Object>> getCyprusEntities() {
        try {
            return jdbcTemplate.queryForList(
                "SELECT gc.company_name, gc.incorporation_country, " +
                "(SELECT COUNT(*) FROM directors d WHERE d.company_id = gc.id) as director_count " +
                "FROM group_companies gc WHERE gc.country = 'Cyprus' OR gc.incorporation_country = 'Cyprus'");
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
    
    private List<Map<String, Object>> getDirectorshipAnalysis() {
        try {
            return jdbcTemplate.queryForList(
                "SELECT d.full_name as director_name, gc.company_name, d.appointment_date, d.position " +
                "FROM directors d JOIN group_companies gc ON d.company_id = gc.id " +
                "ORDER BY d.full_name, d.appointment_date DESC");
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
    
    private List<Map<String, Object>> getDirectorAppointments(String directorName) {
        try {
            return jdbcTemplate.queryForList(
                "SELECT d.*, gc.company_name, gc.country as jurisdiction " +
                "FROM directors d JOIN group_companies gc ON d.company_id = gc.id " +
                "WHERE d.full_name LIKE ? ORDER BY d.appointment_date DESC",
                "%" + directorName + "%");
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
    
    private List<Map<String, Object>> getWCRVarianceData() {
        try {
            return jdbcTemplate.queryForList(
                "SELECT gc.company_name, " +
                "SUM(CASE WHEN wcf.report_date >= '2023-01-01' AND wcf.report_date < '2024-01-01' THEN wcf.limit_amount ELSE 0 END) as amount_2023, " +
                "SUM(CASE WHEN wcf.report_date >= '2024-01-01' THEN wcf.limit_amount ELSE 0 END) as amount_2024 " +
                "FROM working_capital_facilities wcf " +
                "JOIN group_companies gc ON wcf.company_id = gc.id " +
                "GROUP BY gc.company_name " +
                "HAVING amount_2023 > 0 OR amount_2024 > 0 " +
                "ORDER BY (amount_2024 - amount_2023) DESC");
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
    
    // PKO BP specific methods for demo
    private Integer getPKOFacilityCount() {
        try {
            return jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM working_capital_facilities WHERE bank_name LIKE '%PKO BP%'", 
                Integer.class);
        } catch (Exception e) {
            return 0;
        }
    }
    
    private Integer getPKOLargeFacilities() {
        try {
            return jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM working_capital_facilities WHERE bank_name LIKE '%PKO BP%' AND limit_amount > 1000000", 
                Integer.class);
        } catch (Exception e) {
            return 0;
        }
    }
    
    private BigDecimal getPKOAverageFacility() {
        try {
            return jdbcTemplate.queryForObject(
                "SELECT COALESCE(AVG(limit_amount), 0) FROM working_capital_facilities WHERE bank_name LIKE '%PKO BP%'", 
                BigDecimal.class);
        } catch (Exception e) {
            return BigDecimal.ZERO;
        }
    }
    
    private BigDecimal getPKOTotalExposure() {
        try {
            return jdbcTemplate.queryForObject(
                "SELECT COALESCE(SUM(limit_amount), 0) FROM working_capital_facilities WHERE bank_name LIKE '%PKO BP%'", 
                BigDecimal.class);
        } catch (Exception e) {
            return BigDecimal.ZERO;
        }
    }
    
    private BigDecimal getPKOUtilization() {
        try {
            return jdbcTemplate.queryForObject(
                "SELECT COALESCE(AVG(utilization_percentage), 0) FROM working_capital_facilities WHERE bank_name LIKE '%PKO BP%'", 
                BigDecimal.class);
        } catch (Exception e) {
            return BigDecimal.ZERO;
        }
    }
    
    private BigDecimal getPKOConcentration() {
        try {
            BigDecimal pkoTotal = getPKOTotalExposure();
            BigDecimal totalExposure = jdbcTemplate.queryForObject(
                "SELECT COALESCE(SUM(limit_amount), 1) FROM working_capital_facilities", 
                BigDecimal.class);
            return pkoTotal.divide(totalExposure, 4, BigDecimal.ROUND_HALF_UP).multiply(BigDecimal.valueOf(100));
        } catch (Exception e) {
            return BigDecimal.ZERO;
        }
    }
    
    // Utility methods for formatting and assessment
    
    private String formatCurrency(BigDecimal amount) {
        if (amount == null) return "0";
        return String.format("%,.0f", amount);
    }
    
    private String formatPercentage(BigDecimal percentage) {
        if (percentage == null) return "0%";
        return String.format("%.1f%%", percentage);
    }
    
    private String formatDate(Object date) {
        if (date == null) return "N/A";
        return date.toString();
    }
    
    private String getReportTitle(String reportType) {
        switch (reportType.toLowerCase()) {
            case "working_capital_analysis": return "Working Capital Facilities Analysis";
            case "cyprus_entities_governance": return "Cyprus Entities Corporate Governance Report";
            case "financial_variance_analysis": return "Financial Variance Analysis Report";
            case "bank_exposure_analysis": return "Banking Partner Exposure Analysis";
            case "directorship_analysis": return "Executive Directorship Analysis";
            default: return "Executive Business Intelligence Report";
        }
    }
    
    private Map<String, Object> generateWorkingCapitalSummary() {
        Map<String, Object> summary = new HashMap<>();
        summary.put("total_facilities", getTotalAvailableFacilities("EUR").add(getTotalAvailableFacilities("PLN")).add(getTotalAvailableFacilities("BGN")));
        summary.put("total_utilization", getAverageUtilization("EUR"));
        summary.put("banking_partners", getBankingPartnerCount("EUR") + getBankingPartnerCount("PLN") + getBankingPartnerCount("BGN"));
        summary.put("risk_level", "Medium");
        return summary;
    }
    
    private Map<String, Object> generateWorkingCapitalCharts() {
        Map<String, Object> charts = new HashMap<>();
        
        // Currency distribution chart data
        Map<String, BigDecimal> currencyData = new HashMap<>();
        currencyData.put("EUR", getTotalAvailableFacilities("EUR"));
        currencyData.put("PLN", getTotalAvailableFacilities("PLN"));
        currencyData.put("BGN", getTotalAvailableFacilities("BGN"));
        charts.put("currency_distribution", currencyData);
        
        // Utilization trend data
        List<Map<String, Object>> utilizationTrend = new ArrayList<>();
        // Sample trend data
        charts.put("utilization_trend", utilizationTrend);
        
        return charts;
    }
    
    private String calculateVariance(String metric, String period1, String period2) {
        return "5.2%"; // Sample calculation
    }
    
    private String assessRiskLevel(String metric) {
        switch (metric) {
            case "liquidity": return "Low";
            case "utilization": return "Medium";
            case "concentration": return "High";
            default: return "Medium";
        }
    }
    
    private String assessBankRisk(String bankName) {
        if (bankName.contains("PKO BP")) return "Low";
        if (bankName.contains("Erste")) return "Medium";
        return "Low";
    }
    
    private String getBankRecommendation(String bankName) {
        if (bankName.contains("PKO BP")) return "Maintain relationship";
        return "Monitor performance";
    }
    
    private String assessCountryRisk(String country) {
        switch (country) {
            case "Poland": return "Low";
            case "Romania": return "Medium";
            case "Cyprus": return "Low";
            default: return "Medium";
        }
    }
    
    private String assessComplianceStatus(String companyName) {
        return "Compliant"; // Sample assessment
    }
    
    private String calculateTenure(Object appointmentDate) {
        return "2.5 years"; // Sample calculation
    }
    
    private String assessDirectorRisk(String directorName) {
        return "Low"; // Sample assessment
    }
    
    private String assessMetric(Integer value, int min, int max) {
        if (value >= min && value <= max) return "✓ Within Range";
        if (value < min) return "⚠ Below Target";
        return "⚠ Above Target";
    }
    
    private String assessCurrencyMetric(BigDecimal value, long min, long max) {
        if (value.longValue() >= min && value.longValue() <= max) return "✓ Within Range";
        if (value.longValue() < min) return "⚠ Below Target";
        return "⚠ Above Target";
    }
    
    private String assessPercentageMetric(BigDecimal value, int min, int max) {
        if (value.intValue() >= min && value.intValue() <= max) return "✓ Within Range";
        if (value.intValue() < min) return "⚠ Below Target";
        return "⚠ Above Target";
    }
    
    private BigDecimal calculatePercentageVariance(BigDecimal oldValue, BigDecimal newValue) {
        if (oldValue == null || oldValue.equals(BigDecimal.ZERO)) return BigDecimal.ZERO;
        return newValue.subtract(oldValue).divide(oldValue, 4, BigDecimal.ROUND_HALF_UP).multiply(BigDecimal.valueOf(100));
    }
    
    private String analyzeVariance(BigDecimal variance) {
        if (variance.compareTo(BigDecimal.ZERO) > 0) return "↗ Increase";
        if (variance.compareTo(BigDecimal.ZERO) < 0) return "↘ Decrease";
        return "→ No Change";
    }
    
    // Inner classes for report structure
    
    public static class ExecutiveReport {
        private String reportTitle;
        private String reportType;
        private LocalDateTime generatedAt;
        private Map<String, Object> executiveSummary;
        private List<ReportPage> pages = new ArrayList<>();
        private Map<String, Object> chartData;
        
        // Getters and setters
        public String getReportTitle() { return reportTitle; }
        public void setReportTitle(String reportTitle) { this.reportTitle = reportTitle; }
        
        public String getReportType() { return reportType; }
        public void setReportType(String reportType) { this.reportType = reportType; }
        
        public LocalDateTime getGeneratedAt() { return generatedAt; }
        public void setGeneratedAt(LocalDateTime generatedAt) { this.generatedAt = generatedAt; }
        
        public Map<String, Object> getExecutiveSummary() { return executiveSummary; }
        public void setExecutiveSummary(Map<String, Object> executiveSummary) { this.executiveSummary = executiveSummary; }
        
        public List<ReportPage> getPages() { return pages; }
        public void setPages(List<ReportPage> pages) { this.pages = pages; }
        public void addPage(ReportPage page) { this.pages.add(page); }
        
        public Map<String, Object> getChartData() { return chartData; }
        public void setChartData(Map<String, Object> chartData) { this.chartData = chartData; }
    }
    
    public static class ReportPage {
        private int pageNumber;
        private String pageTitle;
        private Map<String, List<String>> columns = new LinkedHashMap<>();
        
        public int getPageNumber() { return pageNumber; }
        public void setPageNumber(int pageNumber) { this.pageNumber = pageNumber; }
        
        public String getPageTitle() { return pageTitle; }
        public void setPageTitle(String pageTitle) { this.pageTitle = pageTitle; }
        
        public Map<String, List<String>> getColumns() { return columns; }
        public void setColumns(Map<String, List<String>> columns) { this.columns = columns; }
        public void addColumn(String columnName, List<String> values) { 
            this.columns.put(columnName, values); 
        }
    }
}