package com.tournament.management.helpers;

import com.tournament.management.dtos.CreateTournamentRequest;
import com.tournament.management.dtos.TournamentResponse;
import com.tournament.management.entities.Tournament;
import com.tournament.common.enums.SportType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TournamentMapperService {

    private final DivisionMapperService divisionMapper;
    private final RuleConfigMapperService ruleConfigMapper;
    private final JudgePanelMapperService judgePanelMapper;

    public TournamentMapperService(DivisionMapperService divisionMapper,
                                   RuleConfigMapperService ruleConfigMapper,
                                   JudgePanelMapperService judgePanelMapper) {
        this.divisionMapper = divisionMapper;
        this.ruleConfigMapper = ruleConfigMapper;
        this.judgePanelMapper = judgePanelMapper;
    }

    public Tournament toEntity(CreateTournamentRequest request) {
        Tournament t = new Tournament();
        t.setName(request.getName());
        t.setStartDate(request.getStartDate());
        t.setEndDate(request.getEndDate());
        t.setSportType(request.getSportType() != null ? request.getSportType() : SportType.GENERIC);
        t.setUsesJudgePanel(request.getUsesJudgePanel());

        if (request.getDivisions() != null) {
            t.setDivisions(divisionMapper.toEntities(request.getDivisions()));
        }

        if (request.getJudgePanel() != null) {
            t.setJudgePanel(judgePanelMapper.toEntity(request.getJudgePanel()));
        }

        return t;
    }

    public TournamentResponse toResponse(Tournament tournament) {
        TournamentResponse res = new TournamentResponse();
        res.setId(tournament.getId());
        res.setName(tournament.getName());
        res.setStartDate(tournament.getStartDate());
        res.setEndDate(tournament.getEndDate());
        res.setSportType(tournament.getSportType());
        res.setUsesJudgePanel(tournament.getUsesJudgePanel());

        if (tournament.getDivisions() != null) {
            res.setDivisions(divisionMapper.toResponses(tournament.getDivisions()));
        }

        if (tournament.getRuleSets() != null && !tournament.getRuleSets().isEmpty()) {
            List<com.tournament.management.entities.RuleConfig> allRuleConfigs = tournament.getRuleSets().stream()
                    .flatMap(rs -> rs.getRuleConfigs().stream())
                    .toList();
            res.setRuleConfigs(ruleConfigMapper.toResponses(allRuleConfigs));
        }

        if (tournament.getJudgePanel() != null) {
            res.setJudgePanel(judgePanelMapper.toResponse(tournament.getJudgePanel()));
        }

        return res;
    }

    public List<TournamentResponse> toResponses(List<Tournament> tournaments) {
        return tournaments.stream().map(this::toResponse).toList();
    }
}
