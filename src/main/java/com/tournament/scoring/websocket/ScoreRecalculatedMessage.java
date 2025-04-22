package com.tournament.scoring.websocket;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScoreRecalculatedMessage {
    private Long sportsmanId;
    private Double totalScore;
    private List<ScoreBroadcastMessage> rawScores;
}