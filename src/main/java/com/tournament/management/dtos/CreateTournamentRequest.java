package com.tournament.management.dtos;

import com.tournament.common.enums.SportType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateTournamentRequest {

    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private SportType sportType;
    private Boolean usesJudgePanel;

    private List<DivisionRequest> divisions;
    private List<RuleConfigRequest> ruleConfigs;
    private JudgePanelRequest judgePanel;
}
