package com.tournament.management.helpers;

import com.tournament.management.dtos.JudgePanelRequest;
import com.tournament.management.dtos.JudgePanelResponse;
import com.tournament.management.entities.JudgePanelConfig;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JudgePanelMapperServiceTest {

    private final JudgePanelMapperService mapper = new JudgePanelMapperService();

    @Test
    void toEntity_and_toResponse() {
        JudgePanelRequest request = new JudgePanelRequest(5, true, false, List.of("ROLE1", "ROLE2"));

        JudgePanelConfig entity = mapper.toEntity(request);

        assertEquals(5, entity.getTotalJudges());
        assertTrue(entity.getAllowOverride());
        assertFalse(entity.getAnonymousScoring());
        assertEquals(2, entity.getJudgeRoles().size());

        entity.setId(10L);

        JudgePanelResponse response = mapper.toResponse(entity);

        assertEquals(10L, response.getId());
        assertEquals(5, response.getTotalJudges());
        assertTrue(response.getAllowOverride());
        assertFalse(response.getAnonymousScoring());
        assertEquals(2, response.getJudgeRoles().size());
    }
}
