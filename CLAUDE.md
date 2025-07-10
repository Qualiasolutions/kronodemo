# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is the Kronospan AI Business Intelligence Platform - a production-ready AI system designed to demonstrate enterprise-grade capabilities for securing a €15,000+ contract. The platform processes real Kronospan business data and converts natural language queries into SQL, generating professional multi-page reports for C-level executives.

## Technology Stack & Constraints

**Mandatory Stack:**
- Backend: Spring Boot 2.7.18 (Java 8 compatible)
- Java Version: Java 8 (cannot upgrade - legacy enterprise constraint)
- Database: H2 (demo) → Oracle 19c (production target)
- Frontend: React 18 + Material-UI or Ant Design
- AI Engine: Ollama + CodeLlama/SQLCoder (local deployment)
- Build Tool: Maven 3.8.x
- Server: Apache Tomcat 9.0

**Critical Constraints:**
- Air-gapped network (ZERO internet connectivity)
- Hardware: 2x GDX Spark servers (20-core ARM, 128GB RAM, 4TB NVMe)
- Performance: Sub-3-second query response requirement
- Scalability: Support 15-20 concurrent users, 200-500 daily queries

## Data Architecture

The platform processes real Kronospan operational data:

**Working Capital Reports:**
- WCR_16_07_2024.xlsx: 358 rows of facility data (July 2024)
- WCR_27_12_2023.xlsx: 358 rows of facility data (December 2023)
- Structure: Company, Country, Currency, Available/Utilized Facilities, Bank, Review Date
- Multi-currency: EUR, PLN, BGN across Poland, Romania, Italy, Malta, Bulgaria

**Group Companies:**
- 100+ companies with directors, shareholdings, incorporation dates
- Corporate structure analysis, directorship tracking
- Key for regulatory compliance and governance queries

## Database Schema

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

-- Group Companies
CREATE TABLE group_companies (
    company_code VARCHAR(10) PRIMARY KEY,
    company_name VARCHAR(200),
    address TEXT,
    incorporation_date DATE,
    incorporation_number VARCHAR(50),
    total_share_capital DECIMAL(15,2),
    currency VARCHAR(3)
);

-- Directors
CREATE TABLE directors (
    id BIGINT PRIMARY KEY,
    company_code VARCHAR(10),
    director_name VARCHAR(100),
    signing_power VARCHAR(200),
    appointment_date DATE,
    FOREIGN KEY (company_code) REFERENCES group_companies(company_code)
);
```

## Demo Requirements

**Critical Demo Scenarios (Must Work Flawlessly):**
1. "Show me all working capital facilities with PKO BP bank over 1 million EUR"
2. "What are the directorships of Matthias Kaindl as at March 2024?"
3. "Compare total available vs utilized facilities between Poland and Romania"
4. "Which companies have facility utilization over 80%?"
5. "Generate executive report on all Cyprus entities with their directors"
6. "Show me facility variance between December 2023 and July 2024"

**Multi-Page Report Engine:**
- Format: 1 description column + 5 data columns per page
- Handle 16-20 total columns across multiple pages
- Professional PDF/HTML output with charts

## Build Commands

**Note:** This repository currently contains only data and documentation. When implementing the application:

```bash
# Backend (Spring Boot + Maven)
mvn clean install          # Build the application
mvn spring-boot:run       # Run development server
mvn test                  # Run unit tests
mvn package               # Create WAR file for Tomcat deployment

# Frontend (React)
npm install               # Install dependencies
npm start                 # Development server
npm run build            # Production build
npm test                 # Run tests
npm run lint             # Code linting

# Database
# H2 console: http://localhost:8080/h2-console
# JDBC URL: jdbc:h2:./database
```

## Development Workflow

1. **Data Import Priority:** Start with Excel/PDF data processing
2. **AI Query Engine:** Implement text-to-SQL with business terminology
3. **API Development:** Build Spring Boot REST endpoints
4. **Frontend:** Create executive-grade React dashboard
5. **Report Engine:** Multi-page PDF generation with charts
6. **Performance:** Optimize for sub-3-second response times

## Business Context

**Key Business Terms:**
- WCR: Working Capital Requirements/Report
- MDF: Medium-Density Fiberboard
- OSB: Oriented Strand Board
- LTL: Long Term Loans
- Facility: Bank credit line/overdraft
- Utilization: Credit usage percentage

**Success Metrics:**
- Query response: < 3 seconds for 90% of requests
- Data accuracy: 100% match with source files
- User roles: Admin, Manager, User access levels
- Target: Impress Christoforos Georgiou (MIS Manager) and secure €15,000+ contract

## File Locations

- Data files: `/Demo_data_1/`, `/Demo_data_2/`, `/data-sources/`
- Master context: `/MASTER_CONTEXT.md`
- Database: `/database.db` (currently empty)
- Source code: To be created in standard Maven/React structure