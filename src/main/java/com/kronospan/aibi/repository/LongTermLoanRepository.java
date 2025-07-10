package com.kronospan.aibi.repository;

import com.kronospan.aibi.model.LongTermLoan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface LongTermLoanRepository extends JpaRepository<LongTermLoan, Long> {
    
    List<LongTermLoan> findByLenderName(String lenderName);
    
    List<LongTermLoan> findByLoanType(String loanType);
    
    List<LongTermLoan> findByCompanyId(Long companyId);
    
    @Query("SELECT ltl FROM LongTermLoan ltl WHERE ltl.maturityDate BETWEEN :startDate AND :endDate")
    List<LongTermLoan> findByMaturityDateBetween(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
    
    @Query("SELECT ltl FROM LongTermLoan ltl WHERE ltl.outstandingAmount > :amount")
    List<LongTermLoan> findByOutstandingAmountGreaterThan(@Param("amount") BigDecimal amount);
    
    @Query("SELECT SUM(ltl.outstandingAmount) FROM LongTermLoan ltl WHERE ltl.company.id = :companyId")
    BigDecimal getTotalOutstandingByCompanyId(@Param("companyId") Long companyId);
}