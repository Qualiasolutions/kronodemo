package com.kronospan.aibi.repository;

import com.kronospan.aibi.model.Director;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DirectorRepository extends JpaRepository<Director, Long> {
    
    List<Director> findByCompanyId(Long companyId);
    
    List<Director> findByIsActive(Boolean isActive);
    
    List<Director> findByPosition(String position);
    
    @Query("SELECT d FROM Director d WHERE d.fullName LIKE %:name%")
    List<Director> findByFullNameContaining(@Param("name") String name);
    
    @Query("SELECT d FROM Director d WHERE d.company.id = :companyId AND d.isActive = true")
    List<Director> findActiveDirectorsByCompanyId(@Param("companyId") Long companyId);
}