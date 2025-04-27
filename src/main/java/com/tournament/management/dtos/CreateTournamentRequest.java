package com.tournament.management.dtos;

import com.tournament.judges.dtos.JudgeAssignmentRequest;
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
    private String sportName;
    private Boolean usesJudgePanel;

    private List<DivisionRequest> divisions;
    private List<Long> ruleSetIds;
    private JudgePanelRequest judgePanel;

    private List<JudgeAssignmentRequest> judgeAssignments;

}
