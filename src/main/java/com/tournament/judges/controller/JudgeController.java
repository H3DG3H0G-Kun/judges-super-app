package com.tournament.judges.controller;

import com.tournament.judges.dtos.CreateJudgeRequest;
import com.tournament.judges.dtos.JudgeResponse;
import com.tournament.judges.services.JudgeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/judges")
@RequiredArgsConstructor
public class JudgeController {

    private final JudgeService service;

    @PostMapping
    public ResponseEntity<JudgeResponse> create(@RequestBody CreateJudgeRequest request) {
        return ResponseEntity.ok(service.addJudge(request));
    }

    @GetMapping("/tournament/{tournamentId}")
    public ResponseEntity<List<JudgeResponse>> getByTournament(@PathVariable Long tournamentId) {
        return ResponseEntity.ok(service.getJudgesByTournament(tournamentId));
    }
}
