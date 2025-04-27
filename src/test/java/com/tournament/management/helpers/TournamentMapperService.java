package com.tournament.management.helpers;

import com.tournament.management.dtos.*;
import com.tournament.management.entities.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TournamentMapperServiceTest {

    private TournamentMapperService mapper;

    private DivisionMapperService divisionMapper = new DivisionMapperService();
    private RuleConfigMapperService ruleConfigMapper = new RuleConfigMapperService();
    private JudgePanelMapperService judgePanelMapper = new JudgePanelMapperService();

    @BeforeEach
    void setUp() {
        mapper = new TournamentMapperService(divisionMapper, ruleConfigMapper, judgePanelMapper);
    }

    @Test
    void toEntity_and_toResponse() {
        DivisionRequest divReq = new DivisionRequest("Div1", "Desc1");
        JudgePanelRequest panelReq = new JudgePanelRequest(3, true, false, List.of("ROLE1"));

        CreateTournamentRequest request = new CreateTournamentRequest();
        request.setName("Tournament1");
        request.setStartDate(LocalDate.now());
        request.setEndDate(LocalDate.now().plusDays(1));
        request.setSportName("Karate");
        request.setUsesJudgePanel(true);
        request.setDivisions(List.of(divReq));
        request.setJudgePanel(panelReq);

        Tournament entity = mapper.toEntity(request);

        assertEquals("Tournament1", entity.getName());
        assertEquals("Karate", entity.getSportName());
        assertNotNull(entity.getDivisions());
        assertEquals(1, entity.getDivisions().size());
        assertNotNull(entity.getJudgePanel());

        // Setup RuleSets for toResponse test
        RuleConfig rc = new RuleConfig();
        rc.setId(1L);
        rc.setRuleKey("key1");
        rc.setRuleLabel("label1");

        RuleSet rs = new RuleSet();
        rs.setId(1L);
        rs.setRuleConfigs(List.of(rc));

        entity.setRuleSets(List.of(rs));
        entity.setId(100L);

        JudgePanelConfig panelConfig = new JudgePanelConfig();
        panelConfig.setId(10L);
        entity.setJudgePanel(panelConfig);

        TournamentResponse response = mapper.toResponse(entity);

        assertEquals(100L, response.getId());
        assertEquals("Tournament1", response.getName());
        assertEquals("Karate", response.getSportName());
        assertNotNull(response.getDivisions());
        assertEquals(1, response.getDivisions().size());
        assertNotNull(response.getRuleConfigs());
        assertEquals(1, response.getRuleConfigs().size());
        assertNotNull(response.getJudgePanel());
    }

    @Test
    void toResponses_returnsList() {
        Tournament t1 = new Tournament();
        Tournament t2 = new Tournament();

        TournamentResponse r1 = new TournamentResponse();
        TournamentResponse r2 = new TournamentResponse();

        List<Tournament> tournaments = List.of(t1, t2);

        TournamentMapperService spyMapper = org.mockito.Mockito.spy(mapper);
        org.mockito.Mockito.doReturn(r1).when(spyMapper).toResponse(t1);
        org.mockito.Mockito.doReturn(r2).when(spyMapper).toResponse(t2);

        var responses = spyMapper.toResponses(tournaments);

        assertEquals(2, responses.size());
        assertEquals(r1, responses.get(0));
        assertEquals(r2, responses.get(1));
    }
}