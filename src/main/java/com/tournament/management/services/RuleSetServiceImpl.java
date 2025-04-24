package com.tournament.management.services;

import com.tournament.management.dtos.CreateRuleSetRequest;
import com.tournament.management.dtos.RuleSetResponse;
import com.tournament.management.entities.RuleConfig;
import com.tournament.management.entities.RuleSet;
import com.tournament.management.helpers.RuleConfigMapperService;
import com.tournament.management.helpers.RuleSetMapperService;
import com.tournament.management.repositories.RuleConfigRepository;
import com.tournament.management.repositories.RuleSetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RuleSetServiceImpl implements RuleSetService {

    private final RuleSetRepository ruleSetRepo;
    private final RuleSetMapperService ruleSetMapper;
    private final RuleConfigRepository ruleConfigRepo;
    private final RuleConfigMapperService ruleConfigMapper;

    @Override
    public RuleSetResponse save(CreateRuleSetRequest request) {
        RuleSet ruleSet = new RuleSet();
        ruleSet.setName(request.getName());
        ruleSet.setDescription(request.getDescription());

        List<RuleConfig> ruleConfigs = request.getRules().stream()
                .map(ruleReq -> {
                    RuleConfig rule = ruleConfigMapper.toEntity(ruleReq);
                    rule.setRuleSet(ruleSet);
                    return rule;
                })
                .collect(Collectors.toList());

        ruleSet.setRuleConfigs(ruleConfigs);
        RuleSet savedRuleSet = ruleSetRepo.save(ruleSet);

        return ruleSetMapper.toResponse(savedRuleSet);
    }

    @Override
    public RuleSetResponse get(Long id) {
        RuleSet ruleSet = ruleSetRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("RuleSet not found"));
        return ruleSetMapper.toResponse(ruleSet);
    }

    @Override
    public List<RuleSetResponse> getAll() {
        return ruleSetRepo.findAll().stream()
                .map(ruleSetMapper::toResponse)
                .toList();
    }
}
