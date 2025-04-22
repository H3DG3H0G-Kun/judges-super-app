package com.tournament.judges.dtos;

import lombok.Data;

@Data
public class CreateJudgeRequest {
    private String name;
    private String role;
    private Long tournamentId;
}
