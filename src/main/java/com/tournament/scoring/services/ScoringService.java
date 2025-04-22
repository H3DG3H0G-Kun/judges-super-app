package com.tournament.scoring.services;

import com.tournament.scoring.websocket.ScoreBroadcastMessage;

public interface ScoringService {
    void submitScore(ScoreBroadcastMessage message);
}
