package com.tournament.scoring.repositories;

import com.tournament.scoring.entities.Score;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScoreRepository extends JpaRepository<Score, Long> {
    List<Score> findBySportsmanId(Long sportsmanId);
    List<Score> findByRuleIdAndSportsmanId(Long ruleId, Long sportsmanId);
    List<Score> findBySportsmanIdAndRound(Long sportsmanId, Integer round);
}
