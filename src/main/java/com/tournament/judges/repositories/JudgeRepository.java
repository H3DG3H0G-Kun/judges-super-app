package com.tournament.judges.repositories;

import com.tournament.judges.entities.Judge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JudgeRepository extends JpaRepository<Judge, Long> {

    List<Judge> findByPanel_Tournament_IdAndTenantId(Long tournamentId, String tenantId);
}
