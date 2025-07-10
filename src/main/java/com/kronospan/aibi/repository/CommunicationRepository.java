package com.kronospan.aibi.repository;

import com.kronospan.aibi.model.Communication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CommunicationRepository extends JpaRepository<Communication, Long> {
    
    List<Communication> findByCommunicationType(String communicationType);
    
    List<Communication> findByStatus(String status);
    
    List<Communication> findByFromParty(String fromParty);
    
    List<Communication> findByToParty(String toParty);
    
    @Query("SELECT c FROM Communication c WHERE c.communicationDate BETWEEN :startDate AND :endDate")
    List<Communication> findByCommunicationDateBetween(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
    
    @Query("SELECT c FROM Communication c WHERE c.subject LIKE %:searchTerm% OR c.content LIKE %:searchTerm%")
    List<Communication> findBySubjectOrContentContaining(@Param("searchTerm") String searchTerm);
}