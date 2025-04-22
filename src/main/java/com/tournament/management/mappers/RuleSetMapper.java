package com.tournament.management.mappers;

import com.tournament.management.dtos.CreateRuleSetRequest;
import com.tournament.management.dtos.RuleSetResponse;
import com.tournament.management.entities.RuleSet;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RuleSetMapper {
    RuleSet toEntity(CreateRuleSetRequest request);
    RuleSetResponse toResponse(RuleSet ruleSet);
}
