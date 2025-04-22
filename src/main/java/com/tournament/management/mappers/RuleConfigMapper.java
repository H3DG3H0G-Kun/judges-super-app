package com.tournament.management.mappers;

import com.tournament.management.dtos.RuleConfigRequest;
import com.tournament.management.dtos.RuleConfigResponse;
import com.tournament.management.entities.RuleConfig;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface RuleConfigMapper {
    RuleConfig toEntity(RuleConfigRequest request);
    RuleConfigResponse toResponse(RuleConfig ruleConfig);
    List<RuleConfig> toEntities(List<RuleConfigRequest> requests);
    List<RuleConfigResponse> toResponses(List<RuleConfig> ruleConfigs);
}
