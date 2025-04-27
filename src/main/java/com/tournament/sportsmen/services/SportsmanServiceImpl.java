package com.tournament.sportsmen.services;

import com.tournament.common.enums.SportsmanRegistrationStatus;
import com.tournament.management.entities.Division;
import com.tournament.management.entities.Tournament;
import com.tournament.management.repositories.DivisionRepository;
import com.tournament.management.repositories.TournamentRepository;
import com.tournament.sportsmen.dtos.RegisterSportsmanRequest;
import com.tournament.sportsmen.dtos.SportsmanResponse;
import com.tournament.sportsmen.dtos.StatusChangeRequest;
import com.tournament.sportsmen.entities.Sportsman;
import com.tournament.sportsmen.helpers.SportsmanMapperService;
import com.tournament.sportsmen.repositories.SportsmanRepository;
import com.tournament.tenant.TenantContextHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SportsmanServiceImpl implements SportsmanService {

    private final SportsmanRepository repository;
    private final TournamentRepository tournamentRepository;
    private final DivisionRepository divisionRepository;
    private final SportsmanMapperService mapper;

    @Override
    public SportsmanResponse register(RegisterSportsmanRequest request) {
        String tenantId = TenantContextHolder.getTenantId();

        Tournament tournament = tournamentRepository.findById(request.getTournamentId())
                .filter(t -> tenantId.equals(t.getTenantId()))
                .orElseThrow(() -> new RuntimeException("Tournament not found or access denied"));

        Division division = divisionRepository.findById(request.getDivisionId())
                .filter(d -> tenantId.equals(d.getTenantId()))
                .orElseThrow(() -> new RuntimeException("Division not found or access denied"));

        Sportsman sportsman = getSportsman(request, tournament, division);

        return mapper.toResponse(repository.save(sportsman));
    }

    private static Sportsman getSportsman(RegisterSportsmanRequest request, Tournament tournament, Division division) {
        Sportsman sportsman = new Sportsman();
        sportsman.setFullName(request.getFullName());
        sportsman.setAge(request.getAge());
        sportsman.setGender(request.getGender());
        sportsman.setClub(request.getClub());
        sportsman.setCountry(request.getCountry());
        sportsman.setTournament(tournament);
        sportsman.setDivision(division);
        sportsman.setTenantId(TenantContextHolder.getTenantId());
        sportsman.setStatus(SportsmanRegistrationStatus.PENDING);
        return sportsman;
    }

    @Override
    public List<SportsmanResponse> getByTournament(Long tournamentId) {
        return repository.findByTournamentIdAndTenantId(tournamentId, TenantContextHolder.getTenantId())
                .stream().map(mapper::toResponse).toList();
    }

    @Override
    public List<SportsmanResponse> getPending() {
        return repository.findByStatusAndTenantId(String.valueOf(SportsmanRegistrationStatus.PENDING), TenantContextHolder.getTenantId())
                .stream().map(mapper::toResponse).toList();
    }

    @Override
    public SportsmanResponse changeStatus(Long id, StatusChangeRequest request) {
        Sportsman sportsman = repository.findByIdAndTenantId(id, TenantContextHolder.getTenantId())
                .orElseThrow(() -> new RuntimeException("Sportsman not found or access denied"));

        sportsman.setStatus(request.getStatus());

        if (request.getStatus() == SportsmanRegistrationStatus.REJECTED) {
            sportsman.setRejectionComment(request.getRejectionComment());
        } else {
            sportsman.setRejectionComment(null);
        }

        return mapper.toResponse(repository.save(sportsman));
    }

}
