package com.tournament.scoring.helpers;

import com.tournament.scoring.dtos.ScoreResponse;
import com.tournament.scoring.dtos.SubmitScoreRequest;
import com.tournament.scoring.entities.Score;
import com.tournament.management.entities.RuleConfig;
import com.tournament.sportsmen.entities.Sportsman;
import org.springframework.stereotype.Service;

@Service
public class ScoreMapperService {

    public Score toEntity(SubmitScoreRequest request, Sportsman sportsman, RuleConfig rule) {
        Score score = new Score();
        score.setJudgeName(request.getJudgeName());
        score.setSportsman(sportsman);
        score.setRule(rule);
        score.setValue(request.getValue());
        score.setRound(request.getRound() != null ? request.getRound() : 1);
        return score;
    }

    public ScoreResponse toResponse(Score score) {
        ScoreResponse res = new ScoreResponse();
        res.setId(score.getId());
        res.setValue(score.getValue());
        res.setJudgeName(score.getJudgeName());
        res.setRound(score.getRound());
        if (score.getSportsman() != null) {
            res.setSportsmanName(score.getSportsman().getFullName());
        }
        if (score.getRule() != null) {
            res.setRuleLabel(score.getRule().getRuleLabel());
        }
        return res;
    }
}
