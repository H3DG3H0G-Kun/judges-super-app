package com.tournament.management.helpers;

import com.tournament.management.dtos.RuleConfigRequest;
import com.tournament.management.dtos.RuleConfigResponse;
import com.tournament.management.entities.RuleConfig;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RuleConfigMapperService {

    public RuleConfig toEntity(RuleConfigRequest request) {
        RuleConfig config = new RuleConfig();
        config.setRuleKey(request.getRuleKey());
        config.setRuleLabel(request.getRuleLabel());
        config.setMaxScore(request.getMaxScore());
        config.setRequired(request.getRequired());
        config.setRound(request.getRound());
        return config;
    }

    public RuleConfigResponse toResponse(RuleConfig config) {
        RuleConfigResponse res = new RuleConfigResponse();
        res.setId(config.getId());
        res.setRuleKey(config.getRuleKey());
        res.setRuleLabel(config.getRuleLabel());
        res.setMaxScore(config.getMaxScore());
        res.setRequired(config.getRequired());
        res.setRound(config.getRound());
        return res;
    }

    public List<RuleConfig> toEntities(List<RuleConfigRequest> requests) {
        return requests.stream().map(this::toEntity).toList();
    }

    public List<RuleConfigResponse> toResponses(List<RuleConfig> configs) {
        return configs.stream().map(this::toResponse).toList();
    }
}
