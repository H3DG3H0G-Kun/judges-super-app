package com.tournament.management.mappers;

import com.tournament.management.dtos.CreateTournamentRequest;
import com.tournament.management.dtos.TournamentResponse;
import com.tournament.management.entities.Tournament;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(
        componentModel = "spring",
        uses = { DivisionMapper.class, RuleConfigMapper.class, JudgePanelConfigMapper.class }
)
public interface TournamentMapper {
    TournamentResponse toResponse(Tournament tournament);
    List<TournamentResponse> toResponses(List<Tournament> tournaments);
    Tournament toEntity(CreateTournamentRequest request);
}
