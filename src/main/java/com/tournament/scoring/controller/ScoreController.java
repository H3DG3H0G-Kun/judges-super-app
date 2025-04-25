package com.tournament.scoring.controller;

import com.tournament.scoring.dtos.ScoreResponse;
import com.tournament.scoring.dtos.ScoreSummaryDTO;
import com.tournament.scoring.dtos.SubmitScoreRequest;
import com.tournament.scoring.dtos.TournamentResultDTO;
import com.tournament.scoring.services.ScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/scores")
@RequiredArgsConstructor
public class ScoreController {

    private final ScoreService service;

    @PostMapping
    public ResponseEntity<ScoreResponse> submit(@RequestBody SubmitScoreRequest request) {
        return ResponseEntity.ok(service.submitScore(request));
    }

    @GetMapping("/sportsman/{id}")
    public ResponseEntity<List<ScoreResponse>> getScoresBySportsman(@PathVariable Long id) {
        return ResponseEntity.ok(service.getScoresBySportsman(id));
    }

    @GetMapping("/tournament/{id}")
    public ResponseEntity<List<ScoreResponse>> getScoresByTournament(@PathVariable Long id) {
        return ResponseEntity.ok(service.getScoresByTournament(id));
    }

    @GetMapping("/sportsman/{id}/round/{round}")
    public ResponseEntity<ScoreSummaryDTO> getRoundSummary(
            @PathVariable Long id,
            @PathVariable Integer round
    ) {
        return ResponseEntity.ok(service.getSummaryForRound(id, round));
    }

    @GetMapping("/tournament/{id}/summary")
    public ResponseEntity<List<TournamentResultDTO>> getTournamentSummary(@PathVariable Long id) {
        return ResponseEntity.ok(service.getTournamentSummary(id));
    }
}
