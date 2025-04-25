package com.tournament.management.services;

import com.tournament.management.dtos.CreateRuleSetRequest;
import com.tournament.management.dtos.RuleConfigRequest;
import com.tournament.management.dtos.RuleSetResponse;
import com.tournament.management.entities.RuleConfig;
import com.tournament.management.entities.RuleSet;
import com.tournament.management.helpers.RuleConfigMapperService;
import com.tournament.management.helpers.RuleSetMapperService;
import com.tournament.management.repositories.RuleConfigRepository;
import com.tournament.management.repositories.RuleSetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RuleSetServiceImplTest {

    @Mock
    private RuleSetRepository ruleSetRepo;

    @Mock
    private RuleSetMapperService ruleSetMapper;

    @Mock
    private RuleConfigRepository ruleConfigRepo;

    @Mock
    private RuleConfigMapperService ruleConfigMapper;

    @InjectMocks
    private RuleSetServiceImpl service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save_success() {
        RuleConfigRequest ruleReq = new RuleConfigRequest("key1", "label1", 10, true, 1);
        CreateRuleSetRequest request = new CreateRuleSetRequest("RuleSet1", "Desc", List.of(ruleReq));

        RuleSet ruleSet = new RuleSet();
        ruleSet.setName(request.getName());
        ruleSet.setDescription(request.getDescription());

        RuleConfig ruleConfig = new RuleConfig();
        ruleConfig.setRuleKey("key1");
        ruleConfig.setRuleLabel("label1");

        RuleSet savedRuleSet = new RuleSet();
        savedRuleSet.setId(1L);
        savedRuleSet.setName(request.getName());

        RuleSetResponse response = new RuleSetResponse();
        response.setId(1L);
        response.setName(request.getName());

        when(ruleConfigMapper.toEntity(ruleReq)).thenReturn(ruleConfig);
        when(ruleSetRepo.save(any(RuleSet.class))).thenReturn(savedRuleSet);
        when(ruleSetMapper.toResponse(savedRuleSet)).thenReturn(response);

        RuleSetResponse result = service.save(request);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(ruleConfigMapper).toEntity(ruleReq);
        verify(ruleSetRepo).save(any(RuleSet.class));
        verify(ruleSetMapper).toResponse(savedRuleSet);
    }

    @Test
    void get_found() {
        RuleSet ruleSet = new RuleSet();
        ruleSet.setId(1L);

        RuleSetResponse response = new RuleSetResponse();
        response.setId(1L);

        when(ruleSetRepo.findById(1L)).thenReturn(Optional.of(ruleSet));
        when(ruleSetMapper.toResponse(ruleSet)).thenReturn(response);

        RuleSetResponse result = service.get(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    void get_notFound_throws() {
        when(ruleSetRepo.findById(1L)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> service.get(1L));
        assertEquals("RuleSet not found", ex.getMessage());
    }

    @Test
    void getAll_returnsList() {
        RuleSet rs1 = new RuleSet();
        RuleSet rs2 = new RuleSet();

        RuleSetResponse resp1 = new RuleSetResponse();
        RuleSetResponse resp2 = new RuleSetResponse();

        when(ruleSetRepo.findAll()).thenReturn(List.of(rs1, rs2));
        when(ruleSetMapper.toResponse(rs1)).thenReturn(resp1);
        when(ruleSetMapper.toResponse(rs2)).thenReturn(resp2);

        var result = service.getAll();

        assertEquals(2, result.size());
    }
}
