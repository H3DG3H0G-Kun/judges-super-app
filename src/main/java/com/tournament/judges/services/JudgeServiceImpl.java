package com.tournament.judges.services;

import com.tournament.judges.dtos.CreateJudgeRequest;
import com.tournament.judges.dtos.JudgeResponse;
import com.tournament.judges.entities.Judge;
import com.tournament.judges.helpers.JudgeMapperService;
import com.tournament.judges.repositories.JudgeRepository;
import com.tournament.management.entities.JudgePanelConfig;
import com.tournament.management.entities.Tournament;
import com.tournament.management.repositories.JudgePanelConfigRepository;
import com.tournament.management.repositories.TournamentRepository;
import com.tournament.tenant.TenantContextHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JudgeServiceImpl implements JudgeService {

    private final JudgeRepository judgeRepository;
    private final JudgeMapperService judgeMapperService;
    private final TournamentRepository tournamentRepository;
    private final JudgePanelConfigRepository panelConfigRepository;

    @Override
    public JudgeResponse addJudge(CreateJudgeRequest request) {
        Tournament tournament = tournamentRepository.findById(request.getTournamentId())
                .orElseThrow(() -> new RuntimeException("Tournament not found"));

        JudgePanelConfig panel = panelConfigRepository.findByTournament(tournament)
                .orElseThrow(() -> new RuntimeException("Judge panel not found"));

        Judge judge = Judge.builder()
                .name(request.getName())
                .role(request.getRole())
                .panel(panel)
                .build();

        return judgeMapperService.toResponse(judgeRepository.save(judge));
    }

    @Override
    public List<JudgeResponse> getJudgesByTournament(Long tournamentId) {
        List<Judge> judges = judgeRepository.findByPanel_Tournament_IdAndTenantId(tournamentId, TenantContextHolder.getTenantId());
        return judgeMapperService.toResponses(judges);
    }

}
