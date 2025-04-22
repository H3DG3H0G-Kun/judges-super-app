package com.tournament.management.services;

import com.tournament.management.dtos.CreateTournamentRequest;
import com.tournament.management.dtos.TournamentResponse;
import com.tournament.management.entities.Tournament;
import com.tournament.management.mappers.TournamentMapper;
import com.tournament.management.repositories.TournamentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TournamentServiceImpl implements TournamentService {

    private final TournamentRepository tournamentRepository;
    private final TournamentMapper tournamentMapper;

    @Override
    public TournamentResponse createTournament(CreateTournamentRequest request) {
        Tournament mappedTournament = tournamentMapper.toEntity(request);

        if (mappedTournament.getRuleConfigs() != null) {
            mappedTournament.getRuleConfigs().forEach(rule -> rule.setTournament(mappedTournament));
        }
        if (mappedTournament.getDivisions() != null) {
            mappedTournament.getDivisions().forEach(division -> division.setTournament(mappedTournament));
        }
        if (mappedTournament.getJudgePanel() != null) {
            mappedTournament.getJudgePanel().setTournament(mappedTournament);
        }

        Tournament savedTournament = tournamentRepository.save(mappedTournament);
        return tournamentMapper.toResponse(savedTournament);

    }

    @Override
    public List<TournamentResponse> getAllTournaments() {
        return tournamentMapper.toResponses(tournamentRepository.findAll());
    }

    @Override
    public TournamentResponse getTournamentById(Long id) {
        Tournament tournament = tournamentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tournament not found"));
        return tournamentMapper.toResponse(tournament);
    }

    @Override
    public void deleteTournament(Long id) {
        tournamentRepository.deleteById(id);
    }
}
