package com.tournament.scoring.helpers;

import com.tournament.scoring.dtos.ScoreResponse;
import com.tournament.scoring.entities.Score;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScoreMapperService {

    public ScoreResponse toResponse(Score score) {
        ScoreResponse res = new ScoreResponse();
        res.setId(score.getId());
        res.setValue(score.getValue());
        res.setJudgeName(score.getJudgeName());
        res.setRound(score.getRound());

        // Safe null-checks
        if (score.getRule() != null) {
            res.setRuleLabel(score.getRule().getRuleLabel());
        }

        if (score.getSportsman() != null) {
            res.setSportsmanName(score.getSportsman().getFullName());
        }

        return res;
    }

    public List<ScoreResponse> toResponses(List<Score> scores) {
        return scores.stream().map(this::toResponse).toList();
    }
}


