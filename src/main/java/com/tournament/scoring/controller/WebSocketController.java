package com.tournament.scoring.controller;

import com.tournament.scoring.services.ScoringService;
import com.tournament.scoring.websocket.ScoreBroadcastMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class WebSocketController {

    private final ScoringService scoringService;

    @MessageMapping("/submit-score")
    public void submitScore(ScoreBroadcastMessage message) {
        scoringService.submitScore(message);
    }

}
