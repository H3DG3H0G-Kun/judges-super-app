package com.tournament.management.dtos;

import com.tournament.management.enums.FormulaType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RuleConfigRequest {
    private String ruleKey;
    private String ruleLabel;
    private Double maxScore;
    private Double minScore;
    private Double weight;
    private FormulaType formulaType;
}
