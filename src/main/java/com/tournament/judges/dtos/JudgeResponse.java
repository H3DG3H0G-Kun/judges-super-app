package com.tournament.judges.dtos;

import lombok.Data;

@Data
public class JudgeResponse {
    private Long id;
    private String name;
    private String role;
    private String tournamentName;
}
