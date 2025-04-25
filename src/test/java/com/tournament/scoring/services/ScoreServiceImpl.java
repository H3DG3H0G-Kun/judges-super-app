package com.tournament.scoring.services;

import com.tournament.management.entities.RuleConfig;
import com.tournament.scoring.dtos.ScoreResponse;
import com.tournament.scoring.dtos.SubmitScoreRequest;
import com.tournament.scoring.entities.Score;
import com.tournament.scoring.helpers.ScoreMapperService;
import com.tournament.scoring.repositories.ScoreRepository;
import com.tournament.sportsmen.entities.Sportsman;
import com.tournament.sportsmen.repositories.SportsmanRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ScoreServiceImplTest {

    @Mock
    private ScoreRepository scoreRepo;
    @Mock private SportsmanRepository sportsmanRepo;
    @Mock private com.tournament.management.repositories.RuleConfigRepository ruleRepo;
    @Mock private ScoreMapperService mapper;

    @InjectMocks
    private ScoreServiceImpl service;

    @Test
    void submitScore_success() {
        SubmitScoreRequest request = new SubmitScoreRequest();
        request.setSportsmanId(1L);
        request.setRuleId(2L);
        request.setJudgeName("Judge1");
        request.setValue(9.5);
        request.setRound(1);

        Sportsman sportsman = new Sportsman();
        sportsman.setId(1L);

        RuleConfig rule = new RuleConfig();
        rule.setId(2L);

        Score scoreEntity = new Score();
        scoreEntity.setId(100L);

        ScoreResponse response = new ScoreResponse();
        response.setId(100L);

        when(sportsmanRepo.findById(1L)).thenReturn(Optional.of(sportsman));
        when(ruleRepo.findById(2L)).thenReturn(Optional.of(rule));
        when(scoreRepo.findByRuleIdAndSportsmanId(2L, 1L)).thenReturn(List.of());
        when(scoreRepo.save(any())).thenReturn(scoreEntity);
        when(mapper.toResponse(scoreEntity)).thenReturn(response);

        ScoreResponse result = service.submitScore(request);

        assertNotNull(result);
        assertEquals(100L, result.getId());
    }

    @Test
    void submitScore_alreadyScored_throws() {
        SubmitScoreRequest request = new SubmitScoreRequest();
        request.setSportsmanId(1L);
        request.setRuleId(2L);
        request.setJudgeName("Judge1");
        request.setValue(9.5);

        Sportsman sportsman = new Sportsman(); sportsman.setId(1L);
        RuleConfig rule = new RuleConfig(); rule.setId(2L);

        Score existingScore = new Score(); existingScore.setJudgeName("Judge1");

        when(sportsmanRepo.findById(1L)).thenReturn(Optional.of(sportsman));
        when(ruleRepo.findById(2L)).thenReturn(Optional.of(rule));
        when(scoreRepo.findByRuleIdAndSportsmanId(2L, 1L)).thenReturn(List.of(existingScore));

        RuntimeException ex = assertThrows(RuntimeException.class, () -> service.submitScore(request));
        assertEquals("Judge already scored this sportsman for this rule.", ex.getMessage());
    }

    @Test
    void getScoresBySportsman_returnsList() {
        Score score1 = new Score(); score1.setId(1L);
        Score score2 = new Score(); score2.setId(2L);

        ScoreResponse resp1 = new ScoreResponse(); resp1.setId(1L);
        ScoreResponse resp2 = new ScoreResponse(); resp2.setId(2L);

        when(scoreRepo.findBySportsmanIdAndTenantId(eq(1L), any())).thenReturn(List.of(score1, score2));
        when(mapper.toResponses(List.of(score1, score2))).thenReturn(List.of(resp1, resp2));

        var result = service.getScoresBySportsman(1L);

        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals(2L, result.get(1).getId());
    }
}
