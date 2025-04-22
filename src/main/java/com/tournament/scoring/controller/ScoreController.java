package com.tournament.scoring.controller;

import com.tournament.scoring.dtos.ScoreResponse;
import com.tournament.scoring.dtos.SubmitScoreRequest;
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
    public ResponseEntity<List<ScoreResponse>> getScores(@PathVariable Long id) {
        return ResponseEntity.ok(service.getScoresForSportsman(id));
    }
}
