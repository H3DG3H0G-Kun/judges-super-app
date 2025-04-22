package com.tournament.management.services;

import com.tournament.management.dtos.CreateRuleSetRequest;
import com.tournament.management.dtos.RuleSetResponse;

import java.util.List;

public interface RuleSetService {
    RuleSetResponse save(CreateRuleSetRequest request);
    RuleSetResponse get(Long id);
    List<RuleSetResponse> getAll();
}
