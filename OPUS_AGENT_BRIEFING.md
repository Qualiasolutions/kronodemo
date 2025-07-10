# OPUS AGENT BRIEFING - KRONOSPAN AI BI PLATFORM

## 🎯 YOUR MISSION
You are tasked with building a **production-ready AI Business Intelligence platform** for Kronospan-CY that will secure a **€15,000+ enterprise contract**. This is a high-stakes demo that must **WOW C-level executives** with real-world AI capabilities using actual Kronospan business data.

## 📚 REQUIRED EXPERTISE & KNOWLEDGE AREAS

### Core Technologies You Must Master:
1. **Backend Development:**
   - **Spring Boot 2.7.18** (Java 8 compatible - CANNOT upgrade)
   - **Java 8** enterprise patterns and limitations
   - **Maven 3.8.x** project structure and dependency management
   - **H2 Database** (demo) with Oracle 19c migration path
   - **Apache Tomcat 9.0** deployment (WAR files)

2. **AI & Natural Language Processing:**
   - **Ollama integration** for offline AI deployment
   - **CodeLlama/SQLCoder** models for text-to-SQL conversion
   - **Natural language query processing** with business terminology
   - **Intent recognition** for complex financial queries

3. **Data Processing & Integration:**
   - **Apache POI** for Excel file processing (WCR, LTL data)
   - **Apache PDFBox** for PDF text extraction (financial statements)
   - **HTML parsing** for communication logs and form submissions
   - **Multi-currency calculations** (EUR, PLN, BGN)
   - **Data validation and integrity** across diverse sources

4. **Frontend Development:**
   - **React 18** with modern hooks and state management
   - **Material-UI or Ant Design** for enterprise-grade UI
   - **Chart.js or Recharts** for financial visualizations
   - **Executive dashboard design** principles

5. **Enterprise Architecture:**
   - **Air-gapped deployment** strategies (zero internet connectivity)
   - **Spring Security** with role-based access control
   - **Performance optimization** (sub-3-second query responses)
   - **Scalability** for 15-20 concurrent users
   - **Audit logging** and compliance tracking

### Business Domain Knowledge:
- **Manufacturing/Wood Industry:** MDF, OSB, Particleboard operations
- **Financial Operations:** Working Capital Reports (WCR), Long Term Loans (LTL)
- **Corporate Governance:** Directorship tracking, shareholding structures
- **Multi-Country Operations:** Complex European group structures
- **Banking & Credit:** Facility utilization, bank exposure analysis
- **Regulatory Compliance:** Cyprus entity reporting, NDA requirements

## 📊 DATA ARCHITECTURE YOU'LL WORK WITH

### Complete Data Inventory (Real Kronospan Data):
1. **Working Capital Reports:** 2 Excel files with 358 rows of facility data
2. **Long Term Loans:** LTL_Data.xlsx for loan portfolio analysis
3. **Historical Financials:** 10+ PDF files (Kronospan Asia, Oxnard, Spanaco 2013-2017)
4. **Cyprus Entity Reports:** 4 quarterly PDF reports with group structure
5. **Legal/Demo Documents:** NDA, terms, invoices, specifications
6. **Communications:** HTML email logs and form submissions

### Database Schema (7 Tables):
- `working_capital_facilities` - Bank facilities and utilization
- `long_term_loans` - Loan balances and repayments
- `group_companies` - Corporate structure (100+ entities)
- `directors` - Directorship tracking with appointment dates
- `financial_statements` - Historical financial data
- `documents` - Document repository with text extraction
- `communications` - Email and form submission logs

## 🎪 CRITICAL DEMO SCENARIOS (Must Work Flawlessly)

### 12 Natural Language Queries You Must Support:
1. "Show me all working capital facilities with PKO BP bank over 1 million EUR"
2. "What are the directorships of Matthias Kaindl as at March 2024?"
3. "Compare total available vs utilized facilities between Poland and Romania"
4. "Which companies have facility utilization over 80%?"
5. "Generate executive report on all Cyprus entities with their directors"
6. "Show me facility variance between December 2023 and July 2024"
7. "Analyze long-term loan trends from LTL data versus WCR utilization patterns"
8. "Extract key terms and deadlines from the NDA and demo terms documents"
9. "Compare Oxnard financial performance across 2013, 2015, 2016, and 2017"
10. "Show Spanaco Shipping Services financial trends from 2015 to 2017"
11. "Summarize email communications between Qualia and Kronospan teams"
12. "Generate variance report: Cyprus entities September 2023 vs September 2024"

## 🏗️ IMPLEMENTATION PHASES

### Phase 1: Data Foundation (Priority: CRITICAL)
- Create comprehensive database schema with all 7 tables
- Build robust Excel/PDF/HTML parsers with error handling
- Import all data sources with 100% accuracy validation
- Implement multi-currency calculation engine

### Phase 2: AI Query Engine (Priority: HIGH)
- Setup Ollama with offline CodeLlama/SQLCoder deployment
- Build text-to-SQL conversion with business terminology dictionary
- Implement complex multi-table join logic
- Create document search and text extraction capabilities

### Phase 3: API & Frontend (Priority: HIGH)
- Develop Spring Boot REST API with all endpoints
- Create React dashboard with executive-grade UI
- Implement user authentication (Admin/Manager/User roles)
- Build real-time query interface with visualizations

### Phase 4: Reporting Engine (Priority: MEDIUM)
- Multi-page report generator (1 description + 5 data columns format)
- Professional PDF export with charts and summaries
- Historical trend analysis and variance reporting
- Excel export with pivot table support

### Phase 5: Demo Preparation (Priority: CRITICAL)
- Performance optimization for sub-3-second responses
- Comprehensive testing of all 12 demo scenarios
- Air-gapped deployment documentation
- Executive presentation materials

## ⚠️ CRITICAL CONSTRAINTS & REQUIREMENTS

### Technical Constraints:
- **Java 8 ONLY** (cannot upgrade - legacy enterprise requirement)
- **Air-gapped deployment** (zero internet connectivity allowed)
- **Performance:** Sub-3-second response times for 90% of queries
- **Hardware:** 2x GDX Spark servers (20-core ARM, 128GB RAM)
- **Scalability:** Support 15-20 concurrent users, 200-500 daily queries

### Business Requirements:
- **Data Security:** Full NDA compliance, audit logging
- **Executive Grade:** Professional UI suitable for C-level demonstration
- **Real Data:** Use actual Kronospan operational data (under NDA)
- **Multi-Currency:** Proper EUR/PLN/BGN handling and conversions
- **Offline Operation:** Complete functionality without external dependencies

### Success Metrics:
- **Technical KPIs:** 99% uptime, 100% data accuracy
- **Business Impact:** Demonstrate clear ROI for €15,000+ investment
- **Demo Success:** Make Christoforos Georgiou say "This is exactly what we need!"

## 🎯 YOUR SPECIFIC DELIVERABLES

1. **Complete Spring Boot Application** with Maven structure
2. **React Frontend** with executive dashboard
3. **AI Query Engine** with text-to-SQL conversion
4. **Data Import Services** for all 6 data categories
5. **Multi-Page Report Generator** with professional formatting
6. **Database Setup** with H2 and Oracle compatibility
7. **Deployment Documentation** for air-gapped environments
8. **Testing Suite** covering all demo scenarios

## 📁 PROJECT STRUCTURE & FILES

Review these key files in your workspace:
- `MASTER_CONTEXT.md` - Complete project specification (417 lines)
- `CLAUDE.md` - Technical reference and build commands
- `/Demo_data_1/` - WCR Excel files and historical financial PDFs
- `/Demo_data_2/` - LTL data and Cyprus quarterly reports
- `/data-sources/` - Legal documents, communications, demo specifications
- `database.db` - Empty SQLite database ready for setup

---

## 🤖 CONFIRMATION REQUIRED

**Before you begin, please confirm:**

1. **Do you understand the mission?** Building a production-ready AI BI platform to secure a €15,000+ Kronospan contract through executive demonstration

2. **Do you have the required expertise?** Spring Boot 2.7.18 + Java 8, React 18, Ollama AI integration, multi-data-source processing, enterprise architecture

3. **Are you clear on the constraints?** Air-gapped deployment, Java 8 limitation, sub-3-second performance, NDA compliance

4. **Can you handle the data complexity?** 6 data categories, 7 database tables, 12 demo scenarios, multi-currency operations

5. **Do you need additional MCPs?** Review your current tool access and specify any missing capabilities for:
   - PDF text extraction and OCR
   - Excel file processing
   - Database operations (H2/SQLite)
   - Maven project creation
   - File system operations
   - Web services testing
   - AI model integration
   - Chart/visualization libraries

**Please respond with:**
- ✅ Confirmation of understanding
- 🛠️ List of any additional MCPs/tools you need
- 🎯 Your proposed first steps for Phase 1 implementation

**Let's build something that will WOW the executives and secure this contract!**