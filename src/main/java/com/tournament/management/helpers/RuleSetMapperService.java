package com.tournament.management.helpers;

import com.tournament.management.dtos.RuleSetResponse;
import com.tournament.management.entities.RuleSet;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RuleSetMapperService {

    public RuleSetResponse toResponse(RuleSet ruleSet) {
        RuleSetResponse res = new RuleSetResponse();
        res.setId(ruleSet.getId());
        res.setName(ruleSet.getName());
        res.setDescription(ruleSet.getDescription());
        return res;
    }

    public List<RuleSetResponse> toResponses(List<RuleSet> ruleSets) {
        return ruleSets.stream().map(this::toResponse).collect(Collectors.toList());
    }
}
