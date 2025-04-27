package com.tournament.scoring.services;

import com.tournament.management.entities.RuleConfig;
import com.tournament.management.repositories.RuleConfigRepository;
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
    private final RuleConfigRepository ruleConfigRepository;

    public Double calculateTotalScore(String tenantId, Long sportsmanId, Integer round) {
        List<Score> scores = scoreRepository.findByTenantIdAndSportsmanIdAndRound(tenantId, sportsmanId, round);

        Map<Long, List<Score>> groupedByRule = scores.stream()
                .collect(Collectors.groupingBy(score -> score.getRule().getId()));

        double totalScore = 0.0;

        for (Map.Entry<Long, List<Score>> entry : groupedByRule.entrySet()) {
            Long ruleConfigId = entry.getKey();
            List<Score> ruleScores = entry.getValue();

            RuleConfig ruleConfig = ruleConfigRepository.findById(ruleConfigId)
                    .orElseThrow(() -> new RuntimeException("RuleConfig not found for id " + ruleConfigId));

            totalScore += aggregateRuleScores(ruleConfig, ruleScores);
        }

        return totalScore;
    }

    public ScoreSummaryDTO summarizeRound(String tenantId, Long sportsmanId, Integer round) {
        List<Score> scores = scoreRepository.findByTenantIdAndSportsmanIdAndRound(tenantId, sportsmanId, round);

        if (scores.isEmpty()) {
            throw new RuntimeException("No scores found for this round");
        }

        Map<String, Double> ruleScores = new HashMap<>();
        Set<String> judges = new HashSet<>();

        Map<Long, List<Score>> groupedByRule = scores.stream()
                .collect(Collectors.groupingBy(score -> score.getRule().getId()));

        double total = 0.0;

        for (Map.Entry<Long, List<Score>> entry : groupedByRule.entrySet()) {
            Long ruleConfigId = entry.getKey();
            List<Score> ruleScoresList = entry.getValue();

            RuleConfig ruleConfig = ruleConfigRepository.findById(ruleConfigId)
                    .orElseThrow(() -> new RuntimeException("RuleConfig not found for id " + ruleConfigId));

            Double aggregatedScore = aggregateRuleScores(ruleConfig, ruleScoresList);

            ruleScores.put(ruleConfig.getRuleLabel(), aggregatedScore);
            total += aggregatedScore;

            judges.addAll(ruleScoresList.stream().map(Score::getJudgeName).collect(Collectors.toSet()));
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

            Map<Long, List<Score>> groupedByRule = sportsmanScores.stream()
                    .collect(Collectors.groupingBy(score -> score.getRule().getId()));

            double total = 0.0;

            for (Map.Entry<Long, List<Score>> ruleEntry : groupedByRule.entrySet()) {
                Long ruleConfigId = ruleEntry.getKey();
                List<Score> ruleScores = ruleEntry.getValue();

                RuleConfig ruleConfig = ruleConfigRepository.findById(ruleConfigId)
                        .orElseThrow(() -> new RuntimeException("RuleConfig not found for id " + ruleConfigId));

                total += aggregateRuleScores(ruleConfig, ruleScores);
            }

            TournamentResultDTO dto = new TournamentResultDTO();
            dto.setSportsmanId(sportsmanId);
            dto.setSportsmanName(sportsmanScores.getFirst().getSportsman().getFullName());
            dto.setTotalScore(total);

            results.add(dto);
        }

        return results;
    }

    private Double aggregateRuleScores(RuleConfig ruleConfig, List<Score> scores) {
        List<Double> values = scores.stream()
                .map(Score::getValue)
                .collect(Collectors.toList());

        if (values.isEmpty()) {
            return 0.0;
        }

        return switch (ruleConfig.getFormulaType()) {
            case AVERAGE_ALL -> averageAll(values);
            case DROP_HIGHEST_LOWEST -> averageMiddleTwo(values);
            case WEIGHTED -> weightedAverage(values, ruleConfig.getWeight());
            case SUM -> sumAll(values);
            case BONUS_SINGLE -> sumAll(values);
            case PENALTY_SINGLE -> -sumAll(values);
            default -> throw new RuntimeException("Unsupported formula type: " + ruleConfig.getFormulaType());
        };

    }

    private Double averageAll(List<Double> scores) {
        return scores.stream()
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(0.0);
    }

    private Double averageMiddleTwo(List<Double> scores) {
        if (scores.size() < 4) {
            throw new RuntimeException("Need at least 4 scores for DROP_HIGHEST_LOWEST");
        }

        scores.sort(Double::compareTo);
        scores.removeFirst(); // remove lowest
        scores.removeLast(); // remove highest

        return scores.stream()
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(0.0);
    }

    private Double weightedAverage(List<Double> scores, Double weight) {
        return averageAll(scores) * (weight != null ? weight : 1.0);
    }

    private Double sumAll(List<Double> scores) {
        return scores.stream()
                .mapToDouble(Double::doubleValue)
                .sum();
    }
}
