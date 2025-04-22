package com.tournament.scoring.controller;

import com.tournament.common.websocket.WebSocketPublisher;
import com.tournament.scoring.enums.WebSocketTopic;
import com.tournament.scoring.websocket.JudgeStatusMessage;
import com.tournament.scoring.websocket.MainJudgeOverrideMessage;
import com.tournament.scoring.websocket.SessionEventMessage;
import com.tournament.scoring.websocket.SportsmanMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class JudgePanelWebSocketController {

    private final WebSocketPublisher publisher;

    @MessageMapping("/judge-ready")
    public void judgeReady(JudgeStatusMessage msg) {
        publisher.send(WebSocketTopic.JUDGE_READY, msg.getJudgeId(), msg);
    }

    @MessageMapping("/next-sportsman")
    public void nextSportsman(SportsmanMessage msg) {
        publisher.send(WebSocketTopic.CURRENT_SPORTSMAN, msg.getSportsmanId(), msg);
    }

    @MessageMapping("/lock-judge")
    public void lockJudge(Long judgeId) {
        publisher.send(WebSocketTopic.JUDGE_LOCKED, judgeId, "locked");
    }

    @MessageMapping("/main-judge-override")
    public void override(MainJudgeOverrideMessage msg) {
        publisher.send(WebSocketTopic.MAIN_JUDGE_OVERRIDE, msg.getTournamentId(), msg);
    }

    @MessageMapping("/session-start")
    public void startSession(SessionEventMessage msg) {
        msg.setEventType("SESSION_STARTED");
        publisher.send(WebSocketTopic.SESSION_STARTED, msg.getDivisionId(), msg);
    }

    @MessageMapping("/session-end")
    public void endSession(SessionEventMessage msg) {
        msg.setEventType("SESSION_ENDED");
        publisher.send(WebSocketTopic.SESSION_ENDED, msg.getDivisionId(), msg);
    }
}
