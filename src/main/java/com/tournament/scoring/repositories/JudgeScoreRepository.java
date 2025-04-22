package com.tournament.scoring.repositories;

import com.tournament.scoring.entities.JudgeScore;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JudgeScoreRepository extends JpaRepository<JudgeScore, Long> {

    List<JudgeScore> findBySportsmanId(Long sportsmanId);

    List<JudgeScore> findByTournamentIdAndSportsmanId(Long tournamentId, Long sportsmanId);

    boolean existsBySportsmanIdAndJudgeId(Long sportsmanId, Long judgeId);
}
