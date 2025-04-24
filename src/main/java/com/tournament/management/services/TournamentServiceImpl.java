package com.tournament.management.services;

import com.tournament.management.dtos.CreateTournamentRequest;
import com.tournament.management.dtos.TournamentResponse;
import com.tournament.management.entities.RuleSet;
import com.tournament.management.entities.Tournament;
import com.tournament.management.helpers.TournamentMapperService;
import com.tournament.management.repositories.RuleSetRepository;
import com.tournament.management.repositories.TournamentRepository;
import com.tournament.tenant.TenantContextHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TournamentServiceImpl implements TournamentService {

    private final TournamentRepository tournamentRepository;
    private final TournamentMapperService tournamentMapper;
    private final RuleSetRepository ruleSetRepository;


    @Override
    public TournamentResponse createTournament(CreateTournamentRequest request) {
        Tournament tournament = tournamentMapper.toEntity(request);
        tournament.setTenantId(TenantContextHolder.getTenantId());

        List<RuleSet> ruleSets = ruleSetRepository.findAllById(request.getRuleSetIds());
        tournament.setRuleSets(ruleSets);

        if (tournament.getDivisions() != null) {
            tournament.getDivisions().forEach(division -> division.setTournament(tournament));
        }
        if (tournament.getJudgePanel() != null) {
            tournament.getJudgePanel().setTournament(tournament);
        }

        Tournament savedTournament = tournamentRepository.save(tournament);
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
