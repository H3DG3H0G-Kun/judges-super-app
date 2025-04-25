package com.tournament.judges.services;

import com.tournament.judges.dtos.CreateJudgeRequest;
import com.tournament.judges.dtos.JudgeResponse;
import com.tournament.judges.entities.Judge;
import com.tournament.judges.helpers.JudgeMapperService;
import com.tournament.judges.repositories.JudgeRepository;
import com.tournament.tenant.TenantContextHolder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class JudgeServiceImplTest {

    @Mock private JudgeRepository judgeRepository;
    @Mock
    private JudgeMapperService judgeMapper;

    @InjectMocks
    private JudgeServiceImpl judgeService;

    @Test
    void createJudge_shouldSaveAndReturn() {
        CreateJudgeRequest req = new CreateJudgeRequest(); req.setName("Judge D");
        Judge entity = new Judge(); entity.setName("Judge D");
        JudgeResponse response = new JudgeResponse(); response.setName("Judge D");

        when(judgeRepository.save(entity)).thenReturn(entity);
        when(judgeMapper.toResponse(entity)).thenReturn(response);

        JudgeResponse result = judgeService.addJudge(req);
        assertEquals("Judge D", result.getName());
    }

    @Test
    void getJudgesByTournament_shouldReturnList() {
        when(judgeRepository.findByPanel_Tournament_IdAndTenantId(10L, TenantContextHolder.getTenantId())).thenReturn(List.of(new Judge()));
        when(judgeMapper.toResponses(any())).thenReturn(List.of(new JudgeResponse()));

        List<JudgeResponse> list = judgeService.getJudgesByTournament(10L);
        assertEquals(1, list.size());
    }
}
