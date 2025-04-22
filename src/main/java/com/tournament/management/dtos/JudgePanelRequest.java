package com.tournament.management.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JudgePanelRequest {
    private Integer totalJudges;
    private Boolean allowOverride;
    private Boolean anonymousScoring;
    private List<String> judgeRoles;
}
