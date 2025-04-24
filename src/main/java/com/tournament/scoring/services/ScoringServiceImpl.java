package com.tournament.scoring.services;

import com.tournament.common.websocket.WebSocketPublisher;
import com.tournament.management.entities.JudgePanelConfig;
import com.tournament.management.repositories.JudgePanelConfigRepository;
import com.tournament.scoring.entities.JudgeScore;
import com.tournament.scoring.enums.WebSocketTopic;
import com.tournament.scoring.repositories.JudgeScoreRepository;
import com.tournament.scoring.websocket.PanelStatusMessage;
import com.tournament.scoring.websocket.ScoreBroadcastMessage;
import com.tournament.scoring.websocket.ScoreSubmittedMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScoringServiceImpl implements ScoringService {

    private final JudgeScoreRepository judgeScoreRepository;
    private final WebSocketPublisher publisher;
    private final JudgePanelConfigRepository judgePanelConfigRepository;

    @Override
    public void submitScore(ScoreBroadcastMessage msg) {
        if (judgeScoreRepository.existsBySportsmanIdAndJudgeId(msg.getSportsmanId(), msg.getJudgeId())) return;

        // Save
        judgeScoreRepository.save(JudgeScore.builder()
                .tournamentId(msg.getTournamentId())
                .sportsmanId(msg.getSportsmanId())
                .judgeId(msg.getJudgeId())
                .judgeName(msg.getJudgeName())
                .score(msg.getScore())
                .finalized(msg.isFinalized())
                .build());

        // Re-fetch for current panel status
        List<JudgeScore> scores = judgeScoreRepository.findByTournamentIdAndSportsmanId(
                msg.getTournamentId(), msg.getSportsmanId()
        );

        int total = judgePanelConfigRepository.findByTournamentId(msg.getTournamentId())
                .map(JudgePanelConfig::getTotalJudges)
                .orElse(5);

        boolean allDone = scores.size() >= total;

        publisher.send(WebSocketTopic.SCORES, msg.getTournamentId(), msg);
        publisher.send(WebSocketTopic.SCORE_SUBMITTED, msg.getSportsmanId(),
                new ScoreSubmittedMessage(msg.getSportsmanId(), msg.getJudgeId(), msg.getScore(), allDone));

        publisher.send(WebSocketTopic.PANEL_STATUS, msg.getTournamentId(),
                new PanelStatusMessage(msg.getTournamentId(), scores.size(), total));
    }
}
