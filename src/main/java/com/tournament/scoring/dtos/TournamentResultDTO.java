package com.tournament.scoring.dtos;

import lombok.Data;

@Data
public class TournamentResultDTO {
    private Long sportsmanId;
    private String sportsmanName;
    private Double totalScore;
}
