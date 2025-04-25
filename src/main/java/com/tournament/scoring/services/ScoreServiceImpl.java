package com.tournament.scoring.services;

import com.tournament.management.entities.RuleConfig;
import com.tournament.management.repositories.RuleConfigRepository;
import com.tournament.scoring.dtos.ScoreResponse;
import com.tournament.scoring.dtos.ScoreSummaryDTO;
import com.tournament.scoring.dtos.SubmitScoreRequest;
import com.tournament.scoring.dtos.TournamentResultDTO;
import com.tournament.scoring.entities.Score;
import com.tournament.scoring.helpers.ScoreMapperService;
import com.tournament.scoring.repositories.ScoreRepository;
import com.tournament.sportsmen.entities.Sportsman;
import com.tournament.sportsmen.repositories.SportsmanRepository;
import com.tournament.tenant.TenantContextHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScoreServiceImpl implements ScoreService {

    private final ScoreRepository scoreRepository;
    private final SportsmanRepository sportsmanRepository;
    private final RuleConfigRepository ruleConfigRepository;
    private final ScoreMapperService scoreMapperService;
    private final ScoreAggregationService scoreAggregationService;

    @Override
    public ScoreResponse submitScore(SubmitScoreRequest request) {
        String tenantId = TenantContextHolder.getTenantId();

        Sportsman sportsman = sportsmanRepository.findById(request.getSportsmanId())
                .filter(s -> tenantId.equals(s.getTenantId()))
                .orElseThrow(() -> new RuntimeException("Sportsman not found or access denied"));

        RuleConfig rule = ruleConfigRepository.findById(request.getRuleId())
                .filter(r -> tenantId.equals(r.getTenantId()))
                .orElseThrow(() -> new RuntimeException("Rule not found or access denied"));

        boolean alreadyScored = scoreRepository.findByRuleIdAndSportsmanId(rule.getId(), sportsman.getId())
                .stream()
                .map(obj -> (Score) obj)
                .anyMatch(score -> score.getJudgeName().equals(request.getJudgeName()));

        if (alreadyScored) {
            throw new RuntimeException("Judge already scored this sportsman for this rule.");
        }

        Score score = new Score();
        score.setSportsman(sportsman);
        score.setRule(rule);
        score.setValue(request.getValue());
        score.setJudgeName(request.getJudgeName());
        score.setRound(request.getRound() != null ? request.getRound() : 1);

        return scoreMapperService.toResponse(scoreRepository.save(score));
    }

    @Override
    public List<ScoreResponse> getScoresBySportsman(Long sportsmanId) {
        List<Score> scores = scoreRepository.findBySportsmanIdAndTenantId(sportsmanId, TenantContextHolder.getTenantId());
        return scoreMapperService.toResponses(scores);
    }

    @Override
    public List<ScoreResponse> getScoresByTournament(Long tournamentId) {
        return scoreMapperService.toResponses(
                scoreRepository.findBySportsman_Tournament_IdAndTenantId(tournamentId, TenantContextHolder.getTenantId())
        );
    }

    @Override
    public ScoreSummaryDTO getSummaryForRound(Long sportsmanId, Integer round) {
        String tenantId = TenantContextHolder.getTenantId();
        return scoreAggregationService.summarizeRound(tenantId, sportsmanId, round);
    }

    @Override
    public List<TournamentResultDTO> getTournamentSummary(Long tournamentId) {
        String tenantId = TenantContextHolder.getTenantId();
        return scoreAggregationService.summarizeTournament(tenantId, tournamentId);
    }

}
