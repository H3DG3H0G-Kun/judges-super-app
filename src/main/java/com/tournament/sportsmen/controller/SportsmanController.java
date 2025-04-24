package com.tournament.sportsmen.controller;

import com.tournament.sportsmen.dtos.RegisterSportsmanRequest;
import com.tournament.sportsmen.dtos.SportsmanResponse;
import com.tournament.sportsmen.dtos.StatusChangeRequest;
import com.tournament.sportsmen.services.SportsmanService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sportsmen")
@RequiredArgsConstructor
public class SportsmanController {

    private final SportsmanService service;

    @PostMapping
    public ResponseEntity<SportsmanResponse> register(@Valid @RequestBody RegisterSportsmanRequest request) {
        return ResponseEntity.ok(service.register(request));
    }


    @GetMapping("/tournament/{tournamentId}")
    public ResponseEntity<List<SportsmanResponse>> getByTournament(@PathVariable Long tournamentId) {
        return ResponseEntity.ok(service.getByTournament(tournamentId));
    }

    @GetMapping("/pending")
    public ResponseEntity<List<SportsmanResponse>> getPending() {
        return ResponseEntity.ok(service.getPending());
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<SportsmanResponse> changeStatus(
            @PathVariable Long id,
            @RequestBody StatusChangeRequest request
    ) {
        return ResponseEntity.ok(service.changeStatus(id, request));
    }
}
