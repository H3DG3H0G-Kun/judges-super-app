package com.tournament.management.services;

import com.tournament.management.dtos.CreateTournamentRequest;
import com.tournament.management.dtos.DivisionRequest;
import com.tournament.management.dtos.JudgePanelRequest;
import com.tournament.management.dtos.TournamentResponse;
import com.tournament.management.entities.RuleSet;
import com.tournament.management.entities.Tournament;
import com.tournament.management.helpers.TournamentMapperService;
import com.tournament.management.repositories.RuleSetRepository;
import com.tournament.management.repositories.TournamentRepository;
import com.tournament.tenant.TenantContextHolder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TournamentServiceImplTest {

    @Mock
    private TournamentRepository tournamentRepository;

    @Mock
    private RuleSetRepository ruleSetRepository;

    @Mock
    private TournamentMapperService tournamentMapper;

    @InjectMocks
    private TournamentServiceImpl tournamentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        TenantContextHolder.setTenantId("tenant1");
    }

    @Test
    void createTournament_success() {
        CreateTournamentRequest request = new CreateTournamentRequest();
        request.setName("Test Tournament");
        request.setStartDate(LocalDate.now());
        request.setEndDate(LocalDate.now().plusDays(1));
        request.setSportName("Karate");
        request.setUsesJudgePanel(true);
        request.setRuleSetIds(List.of(1L, 2L));
        request.setDivisions(List.of(new DivisionRequest("Div1", "Desc1")));
        request.setJudgePanel(new JudgePanelRequest(3, true, false, List.of("ROLE1", "ROLE2")));

        Tournament tournamentEntity = new Tournament();
        tournamentEntity.setName(request.getName());
        tournamentEntity.setSportName(request.getSportName());

        List<RuleSet> ruleSets = List.of(new RuleSet(), new RuleSet());

        Tournament savedTournament = new Tournament();
        savedTournament.setId(10L);
        savedTournament.setName(request.getName());

        TournamentResponse response = new TournamentResponse();
        response.setId(10L);
        response.setName(request.getName());

        when(tournamentMapper.toEntity(request)).thenReturn(tournamentEntity);
        when(ruleSetRepository.findAllById(request.getRuleSetIds())).thenReturn(ruleSets);
        when(tournamentRepository.save(any(Tournament.class))).thenReturn(savedTournament);
        when(tournamentMapper.toResponse(savedTournament)).thenReturn(response);

        TournamentResponse result = tournamentService.createTournament(request);

        assertNotNull(result);
        assertEquals(10L, result.getId());
        assertEquals(request.getName(), result.getName());

        verify(tournamentMapper).toEntity(request);
        verify(ruleSetRepository).findAllById(request.getRuleSetIds());
        verify(tournamentRepository).save(tournamentEntity);
        verify(tournamentMapper).toResponse(savedTournament);
    }

    @Test
    void getAllTournaments_returnsList() {
        List<Tournament> tournaments = List.of(new Tournament(), new Tournament());
        List<TournamentResponse> responses = List.of(new TournamentResponse(), new TournamentResponse());

        when(tournamentRepository.findAll()).thenReturn(tournaments);
        when(tournamentMapper.toResponses(tournaments)).thenReturn(responses);

        List<TournamentResponse> result = tournamentService.getAllTournaments();

        assertEquals(2, result.size());
        verify(tournamentRepository).findAll();
        verify(tournamentMapper).toResponses(tournaments);
    }

    @Test
    void getTournamentById_found() {
        Tournament tournament = new Tournament();
        tournament.setId(1L);
        TournamentResponse response = new TournamentResponse();
        response.setId(1L);

        when(tournamentRepository.findById(1L)).thenReturn(Optional.of(tournament));
        when(tournamentMapper.toResponse(tournament)).thenReturn(response);

        TournamentResponse result = tournamentService.getTournamentById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    void getTournamentById_notFound_throws() {
        when(tournamentRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> tournamentService.getTournamentById(1L));
        assertEquals("Tournament not found", ex.getMessage());
    }

    @Test
    void deleteTournament_callsRepository() {
        doNothing().when(tournamentRepository).deleteByIdAndTenantId(1L, "tenant1");

        tournamentService.deleteTournament(1L);

        verify(tournamentRepository).deleteByIdAndTenantId(1L, "tenant1");
    }
}