package com.tournament.scoring.services;

import com.tournament.scoring.dtos.ScoreResponse;
import com.tournament.scoring.dtos.ScoreSummaryDTO;
import com.tournament.scoring.dtos.SubmitScoreRequest;
import com.tournament.scoring.dtos.TournamentResultDTO;

import java.util.List;

public interface ScoreService {

    ScoreResponse submitScore(SubmitScoreRequest request);

    List<ScoreResponse> getScoresBySportsman(Long sportsmanId);

    List<ScoreResponse> getScoresByTournament(Long tournamentId);

    ScoreSummaryDTO getSummaryForRound(Long sportsmanId, Integer round);

    List<TournamentResultDTO> getTournamentSummary(Long tournamentId);

}

