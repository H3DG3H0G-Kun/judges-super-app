package com.tournament.scoring.repositories;

import com.tournament.scoring.entities.Score;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface ScoreRepository extends JpaRepository<Score, Long> {
    List<Score> findByTenantIdAndSportsmanId(String tenantId, Long sportsmanId);

    List<Score> findByTenantIdAndRuleIdAndSportsmanId(String tenantId, Long ruleId, Long sportsmanId);

    List<Score> findByTenantIdAndSportsmanIdAndRound(String tenantId, Long sportsmanId, Integer round);

    Collection<Object> findBySportsmanId(Long sportsmanId);

    Collection<Object> findByRuleIdAndSportsmanId(Long id, Long id1);
}
