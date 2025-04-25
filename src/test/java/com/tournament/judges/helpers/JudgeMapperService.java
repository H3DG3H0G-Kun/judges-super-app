package com.tournament.judges.helpers;

import com.tournament.judges.dtos.JudgeResponse;
import com.tournament.judges.entities.Judge;
import com.tournament.management.entities.JudgePanelConfig;
import com.tournament.management.entities.Tournament;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JudgeMapperServiceTest {

    private final JudgeMapperService mapper = new JudgeMapperService();

    @Test
    void toResponse_withTournamentName() {
        Tournament tournament = new Tournament();
        tournament.setName("Test Tournament");

        JudgePanelConfig panel = new JudgePanelConfig();
        panel.setTournament(tournament);

        Judge judge = new Judge();
        judge.setId(1L);
        judge.setName("Judge1");
        judge.setRole("ROLE1");
        judge.setPanel(panel);

        JudgeResponse response = mapper.toResponse(judge);

        assertEquals(1L, response.getId());
        assertEquals("Judge1", response.getName());
        assertEquals("ROLE1", response.getRole());
        assertEquals("Test Tournament", response.getTournamentName());
    }

    @Test
    void toResponse_withoutPanel() {
        Judge judge = new Judge();
        judge.setId(2L);
        judge.setName("Judge2");
        judge.setRole("ROLE2");
        judge.setPanel(null);

        JudgeResponse response = mapper.toResponse(judge);

        assertEquals(2L, response.getId());
        assertEquals("Judge2", response.getName());
        assertEquals("ROLE2", response.getRole());
        assertNull(response.getTournamentName());
    }

    @Test
    void toResponses_returnsList() {
        Judge judge1 = new Judge();
        judge1.setId(1L);
        judge1.setName("Judge1");
        judge1.setRole("ROLE1");

        Judge judge2 = new Judge();
        judge2.setId(2L);
        judge2.setName("Judge2");
        judge2.setRole("ROLE2");

        List<JudgeResponse> responses = mapper.toResponses(List.of(judge1, judge2));

        assertEquals(2, responses.size());
        assertEquals("Judge1", responses.get(0).getName());
        assertEquals("Judge2", responses.get(1).getName());
    }
}
