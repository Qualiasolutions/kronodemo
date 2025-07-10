package com.kronospan.aibi.repository;

import com.kronospan.aibi.model.WorkingCapitalFacility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface WorkingCapitalFacilityRepository extends JpaRepository<WorkingCapitalFacility, Long> {
    
    List<WorkingCapitalFacility> findByBankName(String bankName);
    
    List<WorkingCapitalFacility> findByFacilityType(String facilityType);
    
    List<WorkingCapitalFacility> findByCurrency(String currency);
    
    List<WorkingCapitalFacility> findByCompanyId(Long companyId);
    
    @Query("SELECT wcf FROM WorkingCapitalFacility wcf WHERE wcf.utilizationPercentage > :threshold")
    List<WorkingCapitalFacility> findByUtilizationPercentageGreaterThan(@Param("threshold") BigDecimal threshold);
    
    @Query("SELECT wcf FROM WorkingCapitalFacility wcf WHERE wcf.limitAmount > :amount")
    List<WorkingCapitalFacility> findByLimitAmountGreaterThan(@Param("amount") BigDecimal amount);
    
    @Query("SELECT wcf FROM WorkingCapitalFacility wcf WHERE wcf.maturityDate BETWEEN :startDate AND :endDate")
    List<WorkingCapitalFacility> findByMaturityDateBetween(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
    
    @Query("SELECT wcf FROM WorkingCapitalFacility wcf WHERE wcf.company.country = :country")
    List<WorkingCapitalFacility> findByCompanyCountry(@Param("country") String country);
    
    @Query("SELECT SUM(wcf.limitAmount) FROM WorkingCapitalFacility wcf WHERE wcf.company.id = :companyId")
    BigDecimal getTotalLimitByCompanyId(@Param("companyId") Long companyId);
}