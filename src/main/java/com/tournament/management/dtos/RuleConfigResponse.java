package com.tournament.management.dtos;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RuleConfigResponse {
    private Long id;
    private String ruleKey;
    private String ruleLabel;
    private Integer maxScore;
    private Boolean required;
    private Integer round;
}
