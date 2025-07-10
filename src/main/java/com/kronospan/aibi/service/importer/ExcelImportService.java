package com.kronospan.aibi.service.importer;

import com.kronospan.aibi.model.WorkingCapitalFacility;
import com.kronospan.aibi.model.LongTermLoan;
import com.kronospan.aibi.model.GroupCompany;
import com.kronospan.aibi.repository.WorkingCapitalFacilityRepository;
import com.kronospan.aibi.repository.LongTermLoanRepository;
import com.kronospan.aibi.repository.GroupCompanyRepository;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

/**
 * Excel Import Service for Kronospan Data
 * 
 * Imports actual Kronospan data from:
 * - WCR_16_07_2024.xlsx (Working Capital Reports)
 * - WCR_27_12_2023.xlsx (Working Capital Reports)
 * - LTL_Data.xlsx (Long Term Loans)
 */
@Service
@Transactional
public class ExcelImportService {
    
    @Autowired
    private WorkingCapitalFacilityRepository wcrRepository;
    
    @Autowired
    private LongTermLoanRepository ltlRepository;
    
    @Autowired
    private GroupCompanyRepository companyRepository;
    
    private final Map<String, GroupCompany> companyCache = new HashMap<>();
    
    /**
     * Import WCR data from Excel files
     */
    public void importWCRData() {
        try {
            System.out.println("Starting WCR data import...");
            
            // Import from both WCR files
            importWCRFile("Demo_data_1/Demo_data_1/WCR_16_07_2024.xlsx");
            importWCRFile("Demo_data_1/Demo_data_1/WCR_27_12_2023.xlsx");
            
            System.out.println("WCR data import completed successfully!");
            
        } catch (Exception e) {
            System.err.println("Error importing WCR data: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Import LTL data from Excel file
     */
    public void importLTLData() {
        try {
            System.out.println("Starting LTL data import...");
            
            importLTLFile("Demo_data_2/Demo_data_2/LTL_Data.xlsx");
            
            System.out.println("LTL data import completed successfully!");
            
        } catch (Exception e) {
            System.err.println("Error importing LTL data: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Import specific WCR Excel file
     */
    private void importWCRFile(String filePath) throws IOException {
        IOUtils.setByteArrayMaxOverride(150000000);  // Handle large files
        try (FileInputStream file = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(file)) {
            
            System.out.println("Processing WCR file: " + filePath);
            
            // Get the first sheet (assuming data is in first sheet)
            Sheet sheet = workbook.getSheetAt(0);
            
            // Find header row and data rows
            Row headerRow = null;
            int dataStartRow = -1;
            
            for (int i = 0; i <= 20; i++) {  // Search more rows
                Row row = sheet.getRow(i);
                if (row != null && containsWCRHeaders(row)) {
                    headerRow = row;
                    dataStartRow = i + 1;
                    break;
                }
            }
            
            if (headerRow == null) {
                System.out.println("No WCR headers found in file: " + filePath);
                return;
            }
            
            // Map column indices
            Map<String, Integer> columnMap = mapWCRColumns(headerRow);
            
            // Process data rows
            int rowCount = 0;
            for (int i = dataStartRow; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row != null && !isEmptyRow(row)) {
                    try {
                        WorkingCapitalFacility facility = createWCRFromRow(row, columnMap, filePath);
                        if (facility != null) {
                            wcrRepository.save(facility);
                            rowCount++;
                        }
                    } catch (Exception e) {
                        System.err.println("Error processing row " + i + ": " + e.getMessage());
                    }
                }
            }
            
            System.out.println("Imported " + rowCount + " WCR records from " + filePath);
        }
    }
    
    /**
     * Import LTL Excel file
     */
    private void importLTLFile(String filePath) throws IOException {
        IOUtils.setByteArrayMaxOverride(150000000);  // Handle large files
        try (FileInputStream file = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(file)) {
            
            System.out.println("Processing LTL file: " + filePath);
            
            Sheet sheet = workbook.getSheetAt(0);
            
            // Find header row
            Row headerRow = null;
            int dataStartRow = -1;
            
            for (int i = 0; i <= 10; i++) {
                Row row = sheet.getRow(i);
                if (row != null && containsLTLHeaders(row)) {
                    headerRow = row;
                    dataStartRow = i + 1;
                    break;
                }
            }
            
            if (headerRow == null) {
                System.out.println("No LTL headers found in file: " + filePath);
                return;
            }
            
            Map<String, Integer> columnMap = mapLTLColumns(headerRow);
            
            int rowCount = 0;
            for (int i = dataStartRow; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row != null && !isEmptyRow(row)) {
                    try {
                        LongTermLoan loan = createLTLFromRow(row, columnMap);
                        if (loan != null) {
                            ltlRepository.save(loan);
                            rowCount++;
                        }
                    } catch (Exception e) {
                        System.err.println("Error processing LTL row " + i + ": " + e.getMessage());
                    }
                }
            }
            
            System.out.println("Imported " + rowCount + " LTL records from " + filePath);
        }
    }
    
    /**
     * Check if row contains WCR headers
     */
    private boolean containsWCRHeaders(Row row) {
        String[] expectedHeaders = {"facility", "bank", "limit", "utilized", "currency", "type"};
        int matches = 0;
        for (String header : expectedHeaders) {
            for (Cell cell : row) {
                if (cell.getCellType() == CellType.STRING && 
                    cell.getStringCellValue().toLowerCase().contains(header)) {
                    matches++;
                    break;
                }
            }
        }
        return matches >= 4;  // Allow partial matches
    }
    
    /**
     * Check if row contains LTL headers
     */
    private boolean containsLTLHeaders(Row row) {
        String[] expectedHeaders = {"loan", "lender", "amount", "outstanding"};
        
        for (String header : expectedHeaders) {
            boolean found = false;
            for (Cell cell : row) {
                if (cell.getCellType() == CellType.STRING && 
                    cell.getStringCellValue().toLowerCase().contains(header)) {
                    found = true;
                    break;
                }
            }
            if (!found) return false;
        }
        return true;
    }
    
    /**
     * Map WCR column names to indices
     */
    private Map<String, Integer> mapWCRColumns(Row headerRow) {
        Map<String, Integer> columnMap = new HashMap<>();
        
        for (Cell cell : headerRow) {
            if (cell.getCellType() == CellType.STRING) {
                String header = cell.getStringCellValue().toLowerCase();
                int colIndex = cell.getColumnIndex();
                
                if (header.contains("facility")) columnMap.put("facility", colIndex);
                else if (header.contains("bank")) columnMap.put("bank", colIndex);
                else if (header.contains("limit")) columnMap.put("limit", colIndex);
                else if (header.contains("utilized")) columnMap.put("utilized", colIndex);
                else if (header.contains("undrawn")) columnMap.put("undrawn", colIndex);
                else if (header.contains("currency")) columnMap.put("currency", colIndex);
                else if (header.contains("type")) columnMap.put("type", colIndex);
                else if (header.contains("utilization")) columnMap.put("utilization", colIndex);
                else if (header.contains("maturity")) columnMap.put("maturity", colIndex);
                else if (header.contains("company")) columnMap.put("company", colIndex);
            }
        }
        
        return columnMap;
    }
    
    /**
     * Map LTL column names to indices
     */
    private Map<String, Integer> mapLTLColumns(Row headerRow) {
        Map<String, Integer> columnMap = new HashMap<>();
        
        for (Cell cell : headerRow) {
            if (cell.getCellType() == CellType.STRING) {
                String header = cell.getStringCellValue().toLowerCase();
                int colIndex = cell.getColumnIndex();
                
                if (header.contains("loan")) columnMap.put("loan", colIndex);
                else if (header.contains("lender")) columnMap.put("lender", colIndex);
                else if (header.contains("amount")) columnMap.put("amount", colIndex);
                else if (header.contains("outstanding")) columnMap.put("outstanding", colIndex);
                else if (header.contains("currency")) columnMap.put("currency", colIndex);
                else if (header.contains("maturity")) columnMap.put("maturity", colIndex);
                else if (header.contains("company")) columnMap.put("company", colIndex);
            }
        }
        
        return columnMap;
    }
    
    /**
     * Create WCR entity from Excel row
     */
    private WorkingCapitalFacility createWCRFromRow(Row row, Map<String, Integer> columnMap, String filePath) {
        WorkingCapitalFacility facility = new WorkingCapitalFacility();
        
        // Extract facility name
        String facilityName = getCellValueAsString(row, columnMap.get("facility"));
        if (facilityName == null || facilityName.trim().isEmpty()) {
            facilityName = "WCR_" + System.currentTimeMillis();
        }
        facility.setFacilityName(facilityName);
        
        // Extract bank name
        String bankName = getCellValueAsString(row, columnMap.get("bank"));
        facility.setBankName(bankName != null ? bankName : "Unknown Bank");
        
        // Extract amounts
        BigDecimal limitAmount = getCellValueAsBigDecimal(row, columnMap.get("limit"));
        facility.setLimitAmount(limitAmount);
        
        BigDecimal utilizedAmount = getCellValueAsBigDecimal(row, columnMap.get("utilized"));
        facility.setUtilizedAmount(utilizedAmount);
        
        BigDecimal undrawnAmount = getCellValueAsBigDecimal(row, columnMap.get("undrawn"));
        facility.setUndrawnAmount(undrawnAmount);
        
        // Calculate utilization percentage
        if (limitAmount != null && utilizedAmount != null && limitAmount.compareTo(BigDecimal.ZERO) > 0) {
            BigDecimal utilization = utilizedAmount.divide(limitAmount, 4, BigDecimal.ROUND_HALF_UP)
                    .multiply(BigDecimal.valueOf(100));
            facility.setUtilizationPercentage(utilization);
        }
        
        // Extract currency
        String currency = getCellValueAsString(row, columnMap.get("currency"));
        facility.setCurrency(currency != null ? currency : "EUR");
        
        // Extract facility type
        String facilityType = getCellValueAsString(row, columnMap.get("type"));
        facility.setFacilityType(facilityType != null ? facilityType : "RC");
        
        // Set report date based on file name
        if (filePath.contains("2024")) {
            facility.setReportDate(LocalDate.of(2024, 7, 16));
        } else if (filePath.contains("2023")) {
            facility.setReportDate(LocalDate.of(2023, 12, 27));
        }
        
        // Get or create company
        String companyName = getCellValueAsString(row, columnMap.get("company"));
        if (companyName == null || companyName.trim().isEmpty()) {
            companyName = "Kronospan Group";
        }
        facility.setCompany(getOrCreateCompany(companyName));
        
        return facility;
    }
    
    /**
     * Create LTL entity from Excel row
     */
    private LongTermLoan createLTLFromRow(Row row, Map<String, Integer> columnMap) {
        LongTermLoan loan = new LongTermLoan();
        
        // Extract loan reference
        String loanRef = getCellValueAsString(row, columnMap.get("loan"));
        loan.setLoanReference(loanRef != null ? loanRef : "LTL_" + System.currentTimeMillis());
        
        // Extract lender
        String lender = getCellValueAsString(row, columnMap.get("lender"));
        loan.setLenderName(lender != null ? lender : "Unknown Lender");
        
        // Extract amounts
        BigDecimal amount = getCellValueAsBigDecimal(row, columnMap.get("amount"));
        loan.setOriginalAmount(amount);
        
        BigDecimal outstanding = getCellValueAsBigDecimal(row, columnMap.get("outstanding"));
        loan.setOutstandingAmount(outstanding);
        
        // Extract currency
        String currency = getCellValueAsString(row, columnMap.get("currency"));
        loan.setCurrency(currency != null ? currency : "EUR");
        
        // Get or create company
        String companyName = getCellValueAsString(row, columnMap.get("company"));
        if (companyName == null || companyName.trim().isEmpty()) {
            companyName = "Kronospan Group";
        }
        loan.setCompany(getOrCreateCompany(companyName));
        
        return loan;
    }
    
    /**
     * Get or create company entity
     */
    private GroupCompany getOrCreateCompany(String companyName) {
        if (companyCache.containsKey(companyName)) {
            return companyCache.get(companyName);
        }
        
        Optional<GroupCompany> existing = companyRepository.findByCompanyNameContaining(companyName)
                .stream().findFirst();
        
        if (existing.isPresent()) {
            companyCache.put(companyName, existing.get());
            return existing.get();
        }
        
        GroupCompany company = new GroupCompany(companyName, "Cyprus");
        if (companyName.toLowerCase().contains("poland")) {
            company.setCountry("Poland");
        } else if (companyName.toLowerCase().contains("romania")) {
            company.setCountry("Romania");
        }
        
        company = companyRepository.save(company);
        companyCache.put(companyName, company);
        return company;
    }
    
    /**
     * Utility methods for cell value extraction
     */
    private String getCellValueAsString(Row row, Integer columnIndex) {
        if (columnIndex == null) return null;
        
        Cell cell = row.getCell(columnIndex);
        if (cell == null) return null;
        
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue().trim();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                }
                return String.valueOf(cell.getNumericCellValue());
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            default:
                return null;
        }
    }
    
    private BigDecimal getCellValueAsBigDecimal(Row row, Integer columnIndex) {
        if (columnIndex == null) return null;
        
        Cell cell = row.getCell(columnIndex);
        if (cell == null) return null;
        
        try {
            switch (cell.getCellType()) {
                case NUMERIC:
                    return BigDecimal.valueOf(cell.getNumericCellValue());
                case STRING:
                    String value = cell.getStringCellValue().trim();
                    value = value.replaceAll("[^0-9.-]", ""); // Remove non-numeric characters
                    return value.isEmpty() ? null : new BigDecimal(value);
                default:
                    return null;
            }
        } catch (Exception e) {
            return null;
        }
    }
    
    private boolean isEmptyRow(Row row) {
        for (Cell cell : row) {
            if (cell != null && cell.getCellType() != CellType.BLANK) {
                return false;
            }
        }
        return true;
    }
}