package com.tournament.judges.repositories;

import com.tournament.judges.entities.Judge;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface JudgeRepository extends JpaRepository<Judge, Long> {
    List<Judge> findByTenantIdAndPanel_Tournament_Id(String tenantId, Long tournamentId);

    List<Judge> findByTenantIdAndPanel_Tournament_IdAndRole(String tenantId, Long tournamentId, String role);

    Collection<Object> findByPanel_Tournament_Id(Long tournamentId);
}
