package com.kronospan.aibi.repository;

import com.kronospan.aibi.model.GroupCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupCompanyRepository extends JpaRepository<GroupCompany, Long> {
    
    Optional<GroupCompany> findByCompanyCode(String companyCode);
    
    List<GroupCompany> findByCountry(String country);
    
    List<GroupCompany> findByIsActive(Boolean isActive);
    
    @Query("SELECT gc FROM GroupCompany gc WHERE gc.companyName LIKE %:name%")
    List<GroupCompany> findByCompanyNameContaining(@Param("name") String name);
    
    @Query("SELECT gc FROM GroupCompany gc WHERE gc.country IN :countries")
    List<GroupCompany> findByCountryIn(@Param("countries") List<String> countries);
}