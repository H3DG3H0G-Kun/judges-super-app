package com.tournament.management.services;

import com.tournament.management.dtos.CreateRuleSetRequest;
import com.tournament.management.dtos.RuleConfigRequest;
import com.tournament.management.dtos.RuleSetResponse;
import com.tournament.management.entities.RuleConfig;
import com.tournament.management.entities.RuleSet;
import com.tournament.management.helpers.RuleConfigMapperService;
import com.tournament.management.helpers.RuleSetMapperService;
import com.tournament.management.repositories.RuleConfigRepository;
import com.tournament.management.repositories.RuleSetRepository;
import com.tournament.tenant.TenantContextHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
        ruleSet.setTenantId(TenantContextHolder.getTenantId());

        RuleSet saved = ruleSetRepo.save(ruleSet);

        for (RuleConfigRequest ruleReq : request.getRules()) {
            RuleConfig config = ruleConfigMapper.toEntity(ruleReq);
            config.setRuleSet(saved);
            config.setTenantId(TenantContextHolder.getTenantId());
            ruleConfigRepo.save(config);
        }

        return ruleSetMapper.toResponse(saved);
    }


    @Override
    public RuleSetResponse get(Long id) {
        RuleSet ruleSet = ruleSetRepo.findByIdAndTenantId(id, TenantContextHolder.getTenantId())
                .orElseThrow(() -> new RuntimeException("RuleSet not found"));
        return ruleSetMapper.toResponse(ruleSet);
    }

    @Override
    public List<RuleSetResponse> getAll() {
        return ruleSetMapper.toResponses(ruleSetRepo.findAllByTenantId(TenantContextHolder.getTenantId()));
    }

}
