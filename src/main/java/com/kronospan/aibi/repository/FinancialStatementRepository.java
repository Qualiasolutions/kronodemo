package com.kronospan.aibi.repository;

import com.kronospan.aibi.model.FinancialStatement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FinancialStatementRepository extends JpaRepository<FinancialStatement, Long> {
    
    List<FinancialStatement> findByCompanyId(Long companyId);
    
    List<FinancialStatement> findByStatementType(String statementType);
    
    List<FinancialStatement> findByPeriodEndDate(LocalDate periodEndDate);
    
    @Query("SELECT fs FROM FinancialStatement fs WHERE fs.company.id = :companyId AND fs.periodEndDate BETWEEN :startDate AND :endDate")
    List<FinancialStatement> findByCompanyIdAndPeriodBetween(@Param("companyId") Long companyId, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
    
    @Query("SELECT fs FROM FinancialStatement fs WHERE fs.company.id = :companyId ORDER BY fs.periodEndDate DESC")
    List<FinancialStatement> findByCompanyIdOrderByPeriodEndDateDesc(@Param("companyId") Long companyId);
}