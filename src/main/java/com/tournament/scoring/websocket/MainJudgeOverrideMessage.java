package com.tournament.scoring.websocket;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MainJudgeOverrideMessage {
    private Long tournamentId;
    private Long sportsmanId;
    private String reason;
}
