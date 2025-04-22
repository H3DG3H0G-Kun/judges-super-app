package com.tournament.management.services;

import com.tournament.management.dtos.CreateTournamentRequest;
import com.tournament.management.dtos.TournamentResponse;

import java.util.List;

public interface TournamentService {
    TournamentResponse createTournament(CreateTournamentRequest request);
    List<TournamentResponse> getAllTournaments();
    TournamentResponse getTournamentById(Long id);
    void deleteTournament(Long id);
}
