package com.tournament.judges.services;

import com.tournament.judges.dtos.CreateJudgeRequest;
import com.tournament.judges.dtos.JudgeResponse;

import java.util.List;

public interface JudgeService {
    JudgeResponse addJudge(CreateJudgeRequest request);
    List<JudgeResponse> getJudgesByTournament(Long tournamentId);
}
