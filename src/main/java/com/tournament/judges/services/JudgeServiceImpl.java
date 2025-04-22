package com.tournament.judges.services;

import com.tournament.judges.dtos.CreateJudgeRequest;
import com.tournament.judges.dtos.JudgeResponse;
import com.tournament.judges.entities.Judge;
import com.tournament.judges.mappers.JudgeMapper;
import com.tournament.judges.repositories.JudgeRepository;
import com.tournament.management.entities.JudgePanelConfig;
import com.tournament.management.entities.Tournament;
import com.tournament.management.repositories.JudgePanelConfigRepository;
import com.tournament.management.repositories.TournamentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JudgeServiceImpl implements JudgeService {

    private final JudgeRepository judgeRepo;
    private final JudgeMapper mapper;
    private final TournamentRepository tournamentRepo;
    private final JudgePanelConfigRepository panelRepo;

    @Override
    public JudgeResponse addJudge(CreateJudgeRequest request) {
        Tournament tournament = tournamentRepo.findById(request.getTournamentId())
                .orElseThrow(() -> new RuntimeException("Tournament not found"));

        JudgePanelConfig panel = panelRepo.findByTournament(tournament)
                .orElseThrow(() -> new RuntimeException("Judge panel not found"));

        Judge judge = Judge.builder()
                .name(request.getName())
                .role(request.getRole())
                .panel(panel)
                .build();

        return mapper.toResponse(judgeRepo.save(judge));
    }

    @Override
    public List<JudgeResponse> getJudgesByTournament(Long tournamentId) {
        return judgeRepo.findByPanel_Tournament_Id(tournamentId)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }
}
