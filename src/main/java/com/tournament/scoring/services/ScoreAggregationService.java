package com.tournament.scoring.services;

import com.tournament.scoring.dtos.ScoreSummaryDTO;
import com.tournament.scoring.dtos.TournamentResultDTO;
import com.tournament.scoring.entities.Score;
import com.tournament.scoring.repositories.ScoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScoreAggregationService {

    private final ScoreRepository scoreRepository;

    public Double calculateTotalScore(String tenantId, Long sportsmanId, Integer round) {
        List<Score> scores = scoreRepository.findByTenantIdAndSportsmanIdAndRound(tenantId, sportsmanId, round);
        return scores.stream().mapToDouble(Score::getValue).sum();
    }

    public ScoreSummaryDTO summarizeRound(String tenantId, Long sportsmanId, Integer round) {
        List<Score> scores = scoreRepository.findByTenantIdAndSportsmanIdAndRound(tenantId, sportsmanId, round);

        if (scores.isEmpty()) {
            throw new RuntimeException("No scores found for this round");
        }

        Map<String, Double> ruleScores = new HashMap<>();
        Set<String> judges = new HashSet<>();
        double total = 0;

        for (Score score : scores) {
            String label = score.getRule().getRuleLabel();
            double value = score.getValue();

            ruleScores.put(label, value);
            total += value;
            judges.add(score.getJudgeName());
        }

        ScoreSummaryDTO dto = new ScoreSummaryDTO();
        dto.setSportsmanId(sportsmanId);
        dto.setRound(round);
        dto.setRuleScores(ruleScores);
        dto.setTotalScore(total);
        dto.setJudges(new ArrayList<>(judges));

        return dto;
    }

    public List<TournamentResultDTO> summarizeTournament(String tenantId, Long tournamentId) {
        List<Score> scores = scoreRepository.findBySportsman_Tournament_IdAndTenantId(tournamentId, tenantId);

        Map<Long, List<Score>> groupedBySportsman = scores.stream()
                .collect(Collectors.groupingBy(score -> score.getSportsman().getId()));

        List<TournamentResultDTO> results = new ArrayList<>();

        for (Map.Entry<Long, List<Score>> entry : groupedBySportsman.entrySet()) {
            Long sportsmanId = entry.getKey();
            List<Score> sportsmanScores = entry.getValue();

            double total = sportsmanScores.stream().mapToDouble(Score::getValue).sum();

            TournamentResultDTO dto = new TournamentResultDTO();
            dto.setSportsmanId(sportsmanId);
            dto.setSportsmanName(sportsmanScores.getFirst().getSportsman().getFullName());
            dto.setTotalScore(total);

            results.add(dto);
        }

        return results;
    }
}
