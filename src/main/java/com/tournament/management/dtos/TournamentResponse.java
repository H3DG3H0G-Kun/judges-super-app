package com.tournament.management.dtos;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TournamentResponse {

    private Long id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private String sportName;
    private Boolean usesJudgePanel;

    private List<DivisionResponse> divisions;
    private List<RuleConfigResponse> ruleConfigs;
    private JudgePanelResponse judgePanel;
}
