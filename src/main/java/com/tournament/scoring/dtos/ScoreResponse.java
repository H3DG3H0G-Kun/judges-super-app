package com.tournament.scoring.dtos;

import lombok.Data;

@Data
public class ScoreResponse {
    private Long id;
    private String sportsmanName;
    private String ruleLabel;
    private Double value;
    private String judgeName;
    private Integer round;
}