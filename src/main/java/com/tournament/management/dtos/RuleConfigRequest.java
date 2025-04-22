package com.tournament.management.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RuleConfigRequest {
    private String ruleKey;       // "TECHNIQUE"
    private String ruleLabel;     // "Technique"
    private Integer maxScore;
    private Boolean required;
    private Integer round;
}
