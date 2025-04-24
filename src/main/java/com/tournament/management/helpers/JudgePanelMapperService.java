package com.tournament.management.helpers;

import com.tournament.management.dtos.JudgePanelRequest;
import com.tournament.management.dtos.JudgePanelResponse;
import com.tournament.management.entities.JudgePanelConfig;
import org.springframework.stereotype.Service;

@Service
public class JudgePanelMapperService {

    public JudgePanelConfig toEntity(JudgePanelRequest request) {
        JudgePanelConfig config = new JudgePanelConfig();
        config.setTotalJudges(request.getTotalJudges());
        config.setAllowOverride(request.getAllowOverride());
        config.setAnonymousScoring(request.getAnonymousScoring());
        config.setJudgeRoles(request.getJudgeRoles());
        return config;
    }

    public JudgePanelResponse toResponse(JudgePanelConfig config) {
        JudgePanelResponse res = new JudgePanelResponse();
        res.setId(config.getId());
        res.setTotalJudges(config.getTotalJudges());
        res.setAllowOverride(config.getAllowOverride());
        res.setAnonymousScoring(config.getAnonymousScoring());
        res.setJudgeRoles(config.getJudgeRoles());
        return res;
    }
}
