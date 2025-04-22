package com.tournament.management.mappers;

import com.tournament.management.dtos.*;
import com.tournament.management.dtos.CreateTournamentRequest;
import com.tournament.management.entities.Division;
import com.tournament.management.entities.JudgePanelConfig;
import com.tournament.management.entities.RuleConfig;
import com.tournament.management.entities.Tournament;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TournamentMapper {

    // === Tournament ===
    @Mapping(target = "ruleConfigs", source = "ruleConfigs")
    @Mapping(target = "divisions", source = "divisions")
    @Mapping(target = "judgePanel", source = "judgePanel")
    TournamentResponse toResponse(Tournament tournament);

    List<TournamentResponse> toResponses(List<Tournament> tournaments);

    // === Create
    @Mapping(target = "id", ignore = true)
    Tournament toEntity(CreateTournamentRequest request);

    // === Divisions ===
    Division toEntity(DivisionRequest request);
    DivisionResponse toResponse(Division division);
    List<Division> toEntities(List<DivisionRequest> requests);
    List<DivisionResponse> toResponsesFromDiv(List<Division> divisions);

    // === RuleConfigs ===
    RuleConfig toEntity(RuleConfigRequest request);
    RuleConfigResponse toResponse(RuleConfig rule);
    List<RuleConfig> toEntitiesFromRules(List<RuleConfigRequest> requests);
    List<RuleConfigResponse> toResponsesFromRules(List<RuleConfig> rules);

    // === JudgePanel ===
    JudgePanelConfig toEntity(JudgePanelRequest request);
    JudgePanelResponse toResponse(JudgePanelConfig config);
}
