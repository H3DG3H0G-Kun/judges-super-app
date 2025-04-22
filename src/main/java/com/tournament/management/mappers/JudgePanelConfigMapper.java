package com.tournament.management.mappers;

import com.tournament.management.dtos.JudgePanelRequest;
import com.tournament.management.dtos.JudgePanelResponse;
import com.tournament.management.entities.JudgePanelConfig;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface JudgePanelConfigMapper {
    JudgePanelConfig toEntity(JudgePanelRequest request);
    JudgePanelResponse toResponse(JudgePanelConfig config);
    List<JudgePanelConfig> toEntities(List<JudgePanelRequest> requests);
    List<JudgePanelResponse> toResponses(List<JudgePanelConfig> configs);
}
