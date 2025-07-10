# KRONOSPAN AI BI PLATFORM - MASTER CONTEXT
## FOR CLAUDE CODE AUTONOMOUS DEVELOPMENT

---

## 🎯 PROJECT MISSION
**Build a production-ready AI Business Intelligence platform demo for Kronospan-CY that will WOW Christoforos Georgiou (MIS Manager) and C-level executives in a live demonstration.**

**Success Metric:** Secure a €15,000+ enterprise contract by demonstrating real-world AI capabilities with their actual business data.

---

## 🏢 BUSINESS CONTEXT

### Company Profile: Kronospan-CY
- **Industry:** Wood-based panel manufacturing (MDF, Particleboard, OSB)
- **Scale:** Major European manufacturer with Cyprus operations
- **Structure:** Complex multi-country group with 100+ subsidiaries
- **Operations:** 15+ countries, multiple currencies (EUR, PLN, BGN, etc.)
- **Key Contact:** Christoforos Georgiou (c.georgiou@kronospan.com.cy, 22273846)

### Critical Business Terminology
- **WCR:** Working Capital Requirements/Report
- **MDF:** Medium-Density Fiberboard
- **OSB:** Oriented Strand Board
- **LTL:** Long Term Loans
- **Facility:** Bank credit line/overdraft
- **Utilization:** How much of available credit is used
- **Directorship:** Board appointments with signing authority
- **Group Structure:** Parent-subsidiary shareholding relationships

### Real Business Pain Points
1. **Manual Query Bottleneck:** IT department overwhelmed with ad-hoc executive data requests
2. **Complex Multi-Entity Analysis:** 100+ companies across countries need instant aggregation
3. **Executive Decision Speed:** C-level needs instant answers about bank exposure, cash liquidity
4. **Regulatory Compliance:** Director appointment tracking, shareholding reporting
5. **Multi-Currency Complexity:** Financial analysis across EUR, PLN, BGN currencies

---

## 📊 AVAILABLE REAL DATA (Complete Inventory)

### 1. Working Capital Reports (Excel Files) - `/Demo_data_1/Demo_data_1/`
- **WCR_16_07_2024.xlsx:** 358 rows of facility data from July 2024
- **WCR_27_12_2023.xlsx:** 358 rows of facility data from December 2023
- **Structure:** Company, Country, Currency, Available Facility, Utilized Facility, Bank, Review Date
- **Countries:** Poland, Romania, Italy, Malta, Bulgaria
- **Banks:** PKO BP S.A., PEKAO SA, UniCredit, ECCM Bank
- **Currencies:** EUR, PLN, BGN

### 2. Long Term Loans Data - `/Demo_data_2/Demo_data_2/`
- **LTL_Data.xlsx:** Long-term loan details for variance analysis
- **Purpose:** Loan balances, repayments, bank group analysis
- **Critical for:** Cash liquidity analysis, debt management queries

### 3. Historical Financial Statements - `/Demo_data_1/Demo_data_1/`
- **K. Asia Holdings cons. 2015.PDF:** Kronospan Asia consolidated financials 2015
- **KRONOPAN ASIA CONS 2016.pdf:** Kronospan Asia consolidated financials 2016
- **Kronospan Asia Holdings Consolidated FS.PDF:** Asia Holdings consolidated statements
- **Oxnard Consolidated Financial Statements 30.09.15.pdf:** Oxnard 2015 financials
- **Oxnard Consolidated Financial Statements 30.09.16.pdf:** Oxnard 2016 financials
- **Oxnard Consolidated Statements 2017.pdf:** Oxnard 2017 financials
- **Oxnard consolidated IFRS 2013.pdf:** Oxnard 2013 IFRS statements
- **SPANACO SHIPPING SERVICES LTD - FS 2017.pdf:** Spanaco 2017 financials
- **SSS Consolidated FS 2016.pdf:** Spanaco Shipping Services 2016 financials
- **Spanaco Shipping Services IFRS 2015.pdf:** Spanaco 2015 IFRS statements
- **Purpose:** Historical trend analysis, multi-year variance reporting

### 4. Cyprus Entity Reports - `/Demo_data_2/Demo_data_2/`
- **CY01_11_07_23.pdf:** Cyprus entity report July 2023
- **CY05_Mar'24.pdf:** Cyprus March 2024 report (includes group companies)
- **CY05_Sep'23.pdf:** Cyprus September 2023 report
- **CY05_Sep'24.pdf:** Cyprus September 2024 report
- **Content:** 100+ companies with directors, shareholdings, incorporation dates
- **Key Data:** Company codes, addresses, share capital, percentage ownership
- **Directors:** Names, signing powers, appointment dates
- **Critical for:** Directorship queries, corporate structure analysis, Cyprus compliance

### 5. Demo Infrastructure Files - `/data-sources/`
- **NDA.pdf:** Non-disclosure agreement for secure data handling
- **demo-inclusions.pdf:** Demo scope and data inclusions specification
- **demo-invoice.pdf:** Demo project invoicing details
- **first-form-submission.pdf:** Initial form submission documentation
- **qualia-terms-and-conditions-for-demo.pdf:** Qualia demo terms and conditions
- **emailing-between-qualia-and-kronospan.html:** Email communications log
- **second-form-submission.html:** Follow-up form submission
- **Purpose:** Legal compliance, demo setup, communication tracking
- **AI Use Cases:** Document extraction, communication analysis, form processing

### 6. Database Foundation
- **database.db:** SQLite database file (currently empty, ready for data ingestion)
- **Location:** `/database.db`

### Data Security & Compliance
- **All data is REAL Kronospan operational data (under NDA)**
- **Air-gapped processing required for production deployment**
- **Legal documents ensure proper data handling and demo boundaries**

---

## 🔧 TECHNICAL SPECIFICATIONS

### Mandatory Technology Stack
```
Backend Framework: Spring Boot 2.7.18 (LAST Java 8 compatible version)
Java Version: Java 8 (CANNOT upgrade - legacy enterprise constraint)
Database: H2 (demo) → Oracle 19c (production target)
Application Server: Apache Tomcat 9.0
Frontend: React 18 + Material-UI or Ant Design
AI Engine: Ollama + CodeLlama/SQLCoder (local deployment)
Build Tool: Maven 3.8.x
PDF Processing: Apache PDFBox + Apache POI for Excel
Charts: Chart.js or Recharts
Security: Spring Security (application-level auth)
Deployment: WAR file for Tomcat
```

### Critical Technical Constraints
- **Air-gapped Network:** ZERO internet connectivity allowed
- **Hardware:** 2x GDX Spark servers (20-core ARM, 128GB RAM, 4TB NVMe)
- **Operating System:** Ubuntu Linux preferred
- **Performance:** Sub-3-second query response requirement
- **Scalability:** Support 15-20 concurrent users, 200-500 daily queries
- **Security:** Complete offline operation, role-based access control

---

## 🎪 DEMO SCENARIOS (Must Work Flawlessly)

### Primary Natural Language Queries
1. **"Show me all working capital facilities with PKO BP bank over 1 million EUR"**
2. **"What are the directorships of Matthias Kaindl as at March 2024?"**
3. **"Compare total available vs utilized facilities between Poland and Romania"**
4. **"Which companies have facility utilization over 80%?"**
5. **"Generate executive report on all Cyprus entities with their directors"**
6. **"Show me facility variance between December 2023 and July 2024"**
7. **"Analyze long-term loan trends from LTL data versus WCR utilization patterns"**
8. **"Extract key terms and deadlines from the NDA and demo terms documents"**
9. **"Compare Oxnard financial performance across 2013, 2015, 2016, and 2017"**
10. **"Show Spanaco Shipping Services financial trends from 2015 to 2017"**
11. **"Summarize email communications between Qualia and Kronospan teams"**
12. **"Generate variance report: Cyprus entities September 2023 vs September 2024"**

### Multi-Page Executive Reports (CRITICAL FEATURE)
- **Format:** 1 description column + 5 data columns per page
- **Capability:** Handle 16-20 total columns across 5 pages
- **Content Types:**
  - Loan balances and repayments by bank group
  - Working capital facilities by country/company
  - Directorship changes over time periods
  - Cash liquidity analysis with variance tracking

### User Access Levels
- **Admin:** Full data access, user management
- **Manager:** Super user access across all data
- **User:** Restricted to assigned company sets

---

## 🎯 WOW FACTORS FOR CHRISTOFOROS

### Technical Excellence
- **Real-time AI:** Convert complex business questions to SQL instantly
- **Enterprise Performance:** Sub-3-second responses on real financial data
- **Professional UI:** Executive-grade dashboard with financial charts
- **Offline Capability:** Complete air-gapped operation demonstration

### Business Impact
- **Real Data Scenarios:** Using actual Kronospan entities and figures
- **Executive Value:** Generate professional multi-page reports instantly
- **Operational Efficiency:** Replace manual IT query requests
- **Regulatory Support:** Instant director appointment tracking

### Competitive Advantages
- **Industry-Specific:** Built for manufacturing/financial complexity
- **Multi-Country Ready:** Handle complex European group structures
- **Bank Integration:** Real bank exposure analysis
- **Currency Handling:** Multi-currency financial calculations

---

## 🏗️ IMPLEMENTATION ARCHITECTURE

### Database Schema (Auto-generate from Excel/PDF data)
```sql
-- Working Capital Facilities
CREATE TABLE working_capital_facilities (
    id BIGINT PRIMARY KEY,
    report_date DATE,
    company_country VARCHAR(50),
    company_name VARCHAR(200),
    currency VARCHAR(3),
    available_facility DECIMAL(15,2),
    utilised_facility DECIMAL(15,2),
    bank_name VARCHAR(100),
    review_date DATE
);

-- Long Term Loans
CREATE TABLE long_term_loans (
    id BIGINT PRIMARY KEY,
    company_code VARCHAR(10),
    loan_type VARCHAR(100),
    bank_name VARCHAR(100),
    currency VARCHAR(3),
    loan_balance DECIMAL(15,2),
    repayment_amount DECIMAL(15,2),
    loan_date DATE,
    maturity_date DATE
);

-- Group Companies
CREATE TABLE group_companies (
    company_code VARCHAR(10) PRIMARY KEY,
    company_name VARCHAR(200),
    address TEXT,
    incorporation_date DATE,
    incorporation_number VARCHAR(50),
    total_share_capital DECIMAL(15,2),
    currency VARCHAR(3),
    country VARCHAR(50),
    entity_type VARCHAR(50)
);

-- Directors
CREATE TABLE directors (
    id BIGINT PRIMARY KEY,
    company_code VARCHAR(10),
    director_name VARCHAR(100),
    signing_power VARCHAR(200),
    appointment_date DATE,
    resignation_date DATE,
    director_type VARCHAR(50),
    FOREIGN KEY (company_code) REFERENCES group_companies(company_code)
);

-- Financial Statements (Historical Data)
CREATE TABLE financial_statements (
    id BIGINT PRIMARY KEY,
    company_code VARCHAR(10),
    statement_type VARCHAR(50), -- 'Consolidated', 'IFRS', etc.
    reporting_period DATE,
    file_source VARCHAR(200),
    revenue DECIMAL(15,2),
    total_assets DECIMAL(15,2),
    total_liabilities DECIMAL(15,2),
    equity DECIMAL(15,2),
    currency VARCHAR(3),
    FOREIGN KEY (company_code) REFERENCES group_companies(company_code)
);

-- Document Repository (for PDF/HTML content)
CREATE TABLE documents (
    id BIGINT PRIMARY KEY,
    document_name VARCHAR(200),
    document_type VARCHAR(50), -- 'NDA', 'Financial Statement', 'Email', etc.
    file_path VARCHAR(500),
    upload_date DATE,
    extracted_text TEXT,
    key_terms TEXT, -- JSON format for extracted terms
    related_entities VARCHAR(500) -- comma-separated company codes
);

-- Communications Log
CREATE TABLE communications (
    id BIGINT PRIMARY KEY,
    communication_type VARCHAR(50), -- 'Email', 'Form Submission'
    sender VARCHAR(100),
    recipient VARCHAR(100),
    subject VARCHAR(200),
    communication_date DATE,
    content TEXT,
    document_id BIGINT,
    FOREIGN KEY (document_id) REFERENCES documents(id)
);
```

### AI Query Processing Pipeline
1. **Natural Language Input** → Text preprocessing
2. **Intent Recognition** → Business context understanding
3. **SQL Generation** → CodeLlama/SQLCoder conversion
4. **Query Execution** → Database interaction
5. **Result Formatting** → Multi-page report generation
6. **Visualization** → Charts and executive dashboards

### Multi-Page Report Engine
- **Input:** Query results with 16-20 columns
- **Processing:** Split into 1 description + 5 data columns per page
- **Output:** Professional PDF/HTML reports
- **Features:** Charts, summaries, executive formatting

---

## ⚠️ RISKS AND MITIGATIONS

To ensure a flawless demo and production readiness, address these potential challenges based on Kronospan's requirements:

### 1. Data Parsing and Accuracy Risks
- **Risk:** Inaccurate extraction from diverse sources (e.g., scanned PDFs like historical financials, malformed HTML in emails, or complex Excel structures in WCR/LTL files) leading to data inconsistencies or query failures.
- **Mitigation:** Implement robust parsing with Apache PDFBox (including OCR fallback), Apache POI for Excel validation, and HTML sanitization. Add data validation scripts during import (Phase 1) with 100% accuracy checks against source files. Test with real samples like CY05 PDFs and email logs.

### 2. Multi-Currency and Calculation Complexity
- **Risk:** Errors in handling EUR/PLN/BGN conversions, summations, or variances (e.g., in loan repayments or facility utilizations) due to offline constraints and legacy Java 8 limitations.
- **Mitigation:** Build a dedicated Multi-Currency Calculation Engine with fixed exchange rates configurable offline. Include unit tests for all currency scenarios and integrate into query validation (Phase 2). Reference pain points like seasonality trends in cash variances.

### 3. Air-Gapped Deployment Constraints
- **Risk:** Installation/dependency issues in zero-internet environments (e.g., downloading Ollama models or Maven packages) or compatibility with GDX Spark ARM hardware.
- **Mitigation:** Provide offline installation guides (e.g., USB-based model transfers) and pre-bundle dependencies in the WAR file. Test on Ubuntu with ARM simulation; ensure Java 8 compatibility and document NDA-compliant data handling.

### 4. Performance and Scalability Bottlenecks
- **Risk:** Exceeding sub-3-second response times for complex queries (e.g., multi-table joins across 100+ entities, 200-500 daily queries) or with 15-20 concurrent users.
- **Mitigation:** Optimize SQL queries with indexes (e.g., on company_code, dates). Implement caching in Spring Boot and stress-test in Phase 5. Monitor with H2 tools, targeting 99% uptime.

### 5. Query Flexibility and Edge Cases
- **Risk:** Natural language queries failing on specifics like random company selections, date ranges (e.g., 'as at dd/mm/yyyy'), or flexible reporting (e.g., custom facility types like LTL/WCR/SWAPS).
- **Mitigation:** Enhance AI intent recognition with Kronospan terminology dictionary. Add error handling for invalid inputs and test all 12+ demo scenarios, including variations from form submissions (e.g., directorship changes over periods).

### 6. Security and Compliance Issues
- **Risk:** Unauthorized access in role-based system (Admin/Manager/User) or breaches in NDA-protected data handling.
- **Mitigation:** Use Spring Security for strict RBAC. Implement audit logging for all queries and data access. Document compliance in Phase 5 demo prep.

These mitigations directly address Kronospan's pain points (e.g., quick retrieval, complex querying) and ensure the platform meets C-level expectations for reliability.

---

## 📋 DEVELOPMENT CHECKLIST

### Phase 1: Data Foundation 
- [ ] Create enhanced database schema with all tables (WCR, LTL, financial statements, documents, communications)
- [ ] Import Excel WCR data (WCR_16_07_2024.xlsx, WCR_27_12_2023.xlsx) into H2 database
- [ ] Import Excel LTL data (LTL_Data.xlsx) for loan analysis
- [ ] Parse PDF Cyprus entity reports (CY01, CY05 series) for group companies and directors
- [ ] Extract text from historical financial PDFs (Kronospan Asia, Oxnard, Spanaco)
- [ ] Process HTML communications and form submissions
- [ ] Parse legal documents (NDA, terms, demo specifications)
- [ ] Build comprehensive data import utilities with error handling
- [ ] Validate data integrity, relationships, and multi-currency consistency
- [ ] Create data lineage tracking for audit purposes

### Phase 2: AI Query Engine 
- [ ] Setup Ollama with CodeLlama/SQLCoder for offline operation
- [ ] Build text-to-SQL conversion service with enhanced schema support
- [ ] Implement comprehensive business terminology dictionary (WCR, LTL, MDF, OSB, etc.)
- [ ] Create multi-table join logic for complex queries
- [ ] Implement document search and text extraction capabilities
- [ ] Add historical trend analysis query patterns
- [ ] Create query validation and error handling with business context
- [ ] Test with all expanded Kronospan scenarios (12 demo queries)
- [ ] Implement multi-currency calculation logic

### Phase 3: API & Frontend 
- [ ] Build Spring Boot REST API
- [ ] Create React dashboard components
- [ ] Implement user authentication/authorization
- [ ] Build real-time query interface
- [ ] Add financial charts and visualizations

### Phase 4: Reporting Engine 
- [ ] Multi-page report generator (1 description + 5 data columns format)
- [ ] Historical trend reporting (multi-year financial analysis)
- [ ] Variance analysis reports (December 2023 vs July 2024, Cyprus quarterly comparisons)
- [ ] Loan portfolio analysis reports (LTL data with bank group breakdowns)
- [ ] Document summary reports (NDA terms, communication logs)
- [ ] PDF export functionality with professional formatting
- [ ] Executive dashboard layout with financial KPIs
- [ ] Chart generation for financial data (utilization trends, loan balances)
- [ ] Export to Excel functionality with pivot table support
- [ ] Multi-currency report formatting (EUR, PLN, BGN)

### Phase 5: Demo Preparation
- [ ] Load complete real Kronospan dataset from all data sources
- [ ] Verify data integrity across all 6 data categories
- [ ] Test all 12 expanded demo scenarios with real data
- [ ] Performance optimization (sub-3-second requirement for complex queries)
- [ ] Stress test with concurrent users and large data sets
- [ ] Create comprehensive demo script covering all data types
- [ ] Prepare executive presentation with real business value examples
- [ ] Document compliance with NDA and air-gapped requirements
- [ ] Prepare for live demonstration with backup scenarios

---

## 🚀 CLAUDE CODE EXECUTION STRATEGY

### Auto-Generated Components Needed
1. **Spring Boot Application** with complete project structure (Maven 3.8.x, Java 8)
2. **Comprehensive Data Import Services** for Excel, PDF, and HTML processing
   - Excel parsers: WCR and LTL data
   - PDF extractors: Financial statements and Cyprus reports
   - HTML processors: Communications and form submissions
   - Document text extraction with OCR capability
3. **Enhanced AI Query Controller** with text-to-SQL conversion for complex schema
4. **React Frontend** with executive dashboard and multi-data-type query interface
5. **Advanced Report Generation Engine** with multi-page capability and historical analysis
6. **Database Configuration** with H2 (demo) and Oracle 19c (production) compatibility
7. **Document Management System** for PDF/HTML content indexing and search
8. **Multi-Currency Calculation Engine** for EUR/PLN/BGN financial operations

### Key Implementation Requirements
- **Use real Kronospan data structures** from all uploaded files (WCR, LTL, financial PDFs, Cyprus reports, communications)
- **Implement actual business scenarios** from expanded requirements (12 demo queries)
- **Generate production-ready code** with proper error handling and data validation
- **Include comprehensive testing** for demo reliability across all data types
- **Optimize for demo presentation** with executive-friendly UI and sub-3-second performance
- **Ensure compliance** with NDA requirements and air-gapped deployment constraints
- **Support multi-currency operations** with proper EUR/PLN/BGN handling
- **Implement document search** with text extraction and key term identification

---

## 🎖️ SUCCESS METRICS

### Technical KPIs
- Query response time: < 3 seconds for 90% of requests
- Concurrent users: 15-20 without performance degradation
- Data accuracy: 100% match with source Excel/PDF data
- Uptime: 99%+ during demo and evaluation period

### Business Impact Metrics
- Executive engagement: C-level actively using system during demo
- Query complexity: Handle multi-table joins and business logic
- Report quality: Professional, presentation-ready outputs
- User adoption simulation: Demonstrate all three user roles

### Demo Success Indicators
- **Christoforos reaction:** Clear excitement and engagement
- **Technical questions:** Deep dive into architecture and capabilities
- **Business scenario validation:** Successful execution of real use cases
- **Next steps discussion:** Movement toward contract negotiation

---

## 🔥 FINAL DIRECTIVE FOR CLAUDE CODE

**BUILD A PRODUCTION-READY KRONOSPAN AI BI PLATFORM THAT:**

1. **Ingests and processes ALL real Kronospan data** from all six data categories (WCR, LTL, financial PDFs, Cyprus reports, communications, legal docs)
2. **Converts natural language queries to SQL** with industry-specific understanding across complex multi-table schemas
3. **Generates professional multi-page reports** with historical analysis, variance reporting, and trend visualization
4. **Demonstrates clear business value** through comprehensive financial analysis and regulatory compliance features
5. **Works completely offline** with zero external dependencies and full air-gapped operation
6. **Handles multi-currency complexity** with EUR/PLN/BGN calculations and conversions
7. **Provides document intelligence** with text extraction, key term identification, and communication analysis
8. **Impresses C-level executives** with enterprise-grade functionality across all business domains
9. **Secures the €15,000+ contract** through superior technical demonstration of real-world capabilities

**The goal is to make Christoforos say "This is exactly what we need!" and move immediately to contract signing.**

**Deploy Spring Boot + React + AI stack using the exact data structures and business scenarios provided. Make it enterprise-grade, fast, and absolutely impressive.**