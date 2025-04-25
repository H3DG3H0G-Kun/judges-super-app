package com.tournament.scoring.repositories;

import com.tournament.scoring.entities.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScoreRepository extends JpaRepository<Score, Long> {

    List<Score> findByRuleIdAndSportsmanId(Long ruleId, Long sportsmanId);

    List<Score> findBySportsmanIdAndTenantId(Long sportsmanId, String tenantId);

    List<Score> findBySportsman_Tournament_IdAndTenantId(Long tournamentId, String tenantId);

    List<Score> findByTenantIdAndSportsmanIdAndRound(String tenantId, Long sportsmanId, Integer round);

    List<Score> findBySportsmanIdAndRound(Long sportsmanId, Integer round);

}
