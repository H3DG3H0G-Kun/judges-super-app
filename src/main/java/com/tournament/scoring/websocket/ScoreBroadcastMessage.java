package com.tournament.scoring.websocket;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScoreBroadcastMessage {
    private Long sportsmanId;
    private Long tournamentId;
    private Long judgeId;
    private String judgeName;
    private Integer score;
    private boolean finalized;
}
