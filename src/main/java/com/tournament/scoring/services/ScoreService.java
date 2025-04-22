package com.tournament.scoring.services;

import com.tournament.scoring.dtos.ScoreResponse;
import com.tournament.scoring.dtos.SubmitScoreRequest;

import java.util.List;

public interface ScoreService {
    ScoreResponse submitScore(SubmitScoreRequest request);
    List<ScoreResponse> getScoresForSportsman(Long sportsmanId);
}
