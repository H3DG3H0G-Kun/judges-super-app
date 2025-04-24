package com.tournament.scoring.services;

import com.tournament.scoring.entities.Score;
import com.tournament.scoring.repositories.ScoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScoreAggregationService {

    private final ScoreRepository scoreRepository;

    public Double calculateTotalScore(String tenantId, Long sportsmanId, Integer round) {
        List<Score> scores = scoreRepository.findByTenantIdAndSportsmanIdAndRound(tenantId, sportsmanId, round);

        // Example aggregation: sum all scores
        // Add business rules here (drop highest/lowest, penalties, etc.)
        return scores.stream()
                .mapToDouble(Score::getValue)
                .sum();
    }
}
