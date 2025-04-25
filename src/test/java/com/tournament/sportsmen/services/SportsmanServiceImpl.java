package com.tournament.sportsmen.services;

import com.tournament.common.enums.SportsmanRegistrationStatus;
import com.tournament.management.entities.Division;
import com.tournament.management.entities.Tournament;
import com.tournament.management.repositories.DivisionRepository;
import com.tournament.management.repositories.TournamentRepository;
import com.tournament.sportsmen.dtos.RegisterSportsmanRequest;
import com.tournament.sportsmen.dtos.StatusChangeRequest;
import com.tournament.sportsmen.entities.Sportsman;
import com.tournament.sportsmen.helpers.SportsmanMapperService;
import com.tournament.sportsmen.repositories.SportsmanRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SportsmanServiceImplTest {

    @Mock private SportsmanRepository sportsmanRepository;
    @Mock private TournamentRepository tournamentRepository;
    @Mock private DivisionRepository divisionRepository;
    @Mock private SportsmanMapperService mapper;

    @InjectMocks private SportsmanServiceImpl service;

    @Test
    void register_success() {
        RegisterSportsmanRequest request = new RegisterSportsmanRequest();
        request.setFullName("John Doe");
        request.setAge(25);
        request.setGender("M");
        request.setClub("Club A");
        request.setCountry("Country X");
        request.setTournamentId(1L);
        request.setDivisionId(2L);

        Tournament tournament = new Tournament(); tournament.setId(1L);
        Division division = new Division(); division.setId(2L);
        Sportsman saved = new Sportsman(); saved.setId(100L);

        when(tournamentRepository.findById(1L)).thenReturn(Optional.of(tournament));
        when(divisionRepository.findById(2L)).thenReturn(Optional.of(division));
        when(sportsmanRepository.save(any(Sportsman.class))).thenReturn(saved);
        when(mapper.toResponse(saved)).thenReturn(new com.tournament.sportsmen.dtos.SportsmanResponse());

        var response = service.register(request);

        assertNotNull(response);
        verify(tournamentRepository).findById(1L);
        verify(divisionRepository).findById(2L);
        verify(sportsmanRepository).save(any(Sportsman.class));
    }

    @Test
    void getByTournament_returnsList() {
        Sportsman s1 = new Sportsman();
        Sportsman s2 = new Sportsman();
        var dto1 = new com.tournament.sportsmen.dtos.SportsmanResponse();
        var dto2 = new com.tournament.sportsmen.dtos.SportsmanResponse();

        when(sportsmanRepository.findByTournamentIdAndTenantId(eq(1L), any()))
                .thenReturn(List.of(s1, s2));
        when(mapper.toResponses(List.of(s1, s2)))
                .thenReturn(List.of(dto1, dto2));

        var result = service.getByTournament(1L);

        assertEquals(2, result.size());
    }

    @Test
    void getPending_returnsList() {
        Sportsman s1 = new Sportsman();
        Sportsman s2 = new Sportsman();
        var dto1 = new com.tournament.sportsmen.dtos.SportsmanResponse();
        var dto2 = new com.tournament.sportsmen.dtos.SportsmanResponse();

        when(sportsmanRepository.findByStatusAndTenantId(String.valueOf(eq(SportsmanRegistrationStatus.PENDING.ordinal())), any()))
                .thenReturn(List.of(s1, s2));
        when(mapper.toResponses(List.of(s1, s2)))
                .thenReturn(List.of(dto1, dto2));

        var result = service.getPending();

        assertEquals(2, result.size());
    }

    @Test
    void changeStatus_approved() {
        Sportsman sportsman = new Sportsman(); sportsman.setId(1L);
        StatusChangeRequest request = new StatusChangeRequest();
        request.setStatus(SportsmanRegistrationStatus.APPROVED);

        when(sportsmanRepository.findByIdAndTenantId(eq(1L), any()))
                .thenReturn(Optional.of(sportsman));
        when(sportsmanRepository.save(any())).thenReturn(sportsman);
        when(mapper.toResponse(sportsman)).thenReturn(new com.tournament.sportsmen.dtos.SportsmanResponse());

        var response = service.changeStatus(1L, request);

        assertNotNull(response);
        assertEquals(SportsmanRegistrationStatus.APPROVED, sportsman.getStatus());
        assertNull(sportsman.getRejectionComment());
    }

    @Test
    void changeStatus_rejected() {
        Sportsman sportsman = new Sportsman(); sportsman.setId(1L);
        StatusChangeRequest request = new StatusChangeRequest();
        request.setStatus(SportsmanRegistrationStatus.REJECTED);
        request.setRejectionComment("Invalid documents");

        when(sportsmanRepository.findByIdAndTenantId(eq(1L), any()))
                .thenReturn(Optional.of(sportsman));
        when(sportsmanRepository.save(any())).thenReturn(sportsman);
        when(mapper.toResponse(sportsman)).thenReturn(new com.tournament.sportsmen.dtos.SportsmanResponse());

        var response = service.changeStatus(1L, request);

        assertNotNull(response);
        assertEquals(SportsmanRegistrationStatus.REJECTED, sportsman.getStatus());
        assertEquals("Invalid documents", sportsman.getRejectionComment());
    }

    @Test
    void changeStatus_sportsmanNotFound_throws() {
        when(sportsmanRepository.findByIdAndTenantId(eq(1L), any()))
                .thenReturn(Optional.empty());

        StatusChangeRequest request = new StatusChangeRequest();
        request.setStatus(SportsmanRegistrationStatus.APPROVED);

        var ex = assertThrows(RuntimeException.class, () -> service.changeStatus(1L, request));
        assertEquals("Sportsman not found or access denied", ex.getMessage());
    }
}
