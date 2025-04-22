package com.tournament.management.services;

import com.tournament.management.dtos.CreateRuleSetRequest;
import com.tournament.management.dtos.RuleSetResponse;
import com.tournament.management.entities.RuleConfig;
import com.tournament.management.entities.RuleSet;
import com.tournament.management.mappers.RuleConfigMapper;
import com.tournament.management.mappers.RuleSetMapper;
import com.tournament.management.repositories.RuleConfigRepository;
import com.tournament.management.repositories.RuleSetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RuleSetServiceImpl implements RuleSetService {

    private final RuleSetRepository ruleSetRepo;
    private final RuleSetMapper ruleSetMapper;
    private final RuleConfigMapper ruleConfigMapper;
    private final RuleConfigRepository ruleConfigRepo;

    @Override
    public RuleSetResponse save(CreateRuleSetRequest request) {
        RuleSet ruleSet = RuleSet.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();

        RuleSet saved = ruleSetRepo.save(ruleSet);

        List<RuleConfig> rules = request.getRules().stream()
                .map(ruleReq -> {
                    RuleConfig rule = ruleConfigMapper.toEntity(ruleReq);
                    rule.setRuleSet(saved);
                    return rule;
                }).toList();

        ruleConfigRepo.saveAll(rules);
        saved.setRuleConfigs(rules);

        return ruleSetMapper.toResponse(saved);
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
