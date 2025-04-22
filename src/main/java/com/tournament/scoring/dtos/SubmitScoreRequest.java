package com.tournament.scoring.dtos;

import lombok.Data;

@Data
public class SubmitScoreRequest {
    private Long sportsmanId;
    private Long ruleId;
    private Double value;
    private String judgeName;
    private Integer round;
}
