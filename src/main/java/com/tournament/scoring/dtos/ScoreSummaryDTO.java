package com.tournament.scoring.dtos;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class ScoreSummaryDTO {
    private Long sportsmanId;
    private Integer round;
    private Map<String, Double> ruleScores;
    private Double totalScore;
    private List<String> judges;
}
