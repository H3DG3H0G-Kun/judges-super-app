package com.tournament.scoring.services;

import com.tournament.management.entities.RuleConfig;
import com.tournament.management.repositories.RuleConfigRepository;
import com.tournament.scoring.dtos.ScoreResponse;
import com.tournament.scoring.dtos.SubmitScoreRequest;
import com.tournament.scoring.entities.Score;
import com.tournament.scoring.helpers.ScoreMapperService;
import com.tournament.scoring.repositories.ScoreRepository;
import com.tournament.sportsmen.entities.Sportsman;
import com.tournament.sportsmen.repositories.SportsmanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScoreServiceImpl implements ScoreService {

    private final ScoreRepository scoreRepo;
    private final SportsmanRepository sportsmanRepo;
    private final RuleConfigRepository ruleRepo;
    private final ScoreMapperService mapper;

    @Override
    public ScoreResponse submitScore(SubmitScoreRequest request) {
        Sportsman sportsman = sportsmanRepo.findById(request.getSportsmanId())
                .orElseThrow(() -> new RuntimeException("Sportsman not found"));

        RuleConfig rule = ruleRepo.findById(request.getRuleId())
                .orElseThrow(() -> new RuntimeException("Rule not found"));

// OPTIONAL: prevent duplicates
        boolean alreadyScored = scoreRepo.findByRuleIdAndSportsmanId(rule.getId(), sportsman.getId())
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

        return mapper.toResponse(scoreRepo.save(score));
    }

    @Override
    public List<ScoreResponse> getScoresForSportsman(Long sportsmanId) {
        return scoreRepo.findBySportsmanId(sportsmanId)
                .stream()
                .map(obj -> mapper.toResponse((Score) obj))
                .toList();
    }
}
