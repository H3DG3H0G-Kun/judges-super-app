package com.tournament.scoring.websocket;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScoreSubmittedMessage {
    private Long sportsmanId;
    private Long judgeId;
    private Integer score;
    private boolean allJudgesSubmitted;
}
