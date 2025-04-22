package com.tournament.sportsmen.services;

import com.tournament.sportsmen.dtos.RegisterSportsmanRequest;
import com.tournament.sportsmen.dtos.SportsmanResponse;
import com.tournament.sportsmen.dtos.StatusChangeRequest;

import java.util.List;

public interface SportsmanService {
    SportsmanResponse register(RegisterSportsmanRequest request);
    List<SportsmanResponse> getByTournament(Long tournamentId);
    List<SportsmanResponse> getPending();
    SportsmanResponse changeStatus(Long id, StatusChangeRequest request);
}
