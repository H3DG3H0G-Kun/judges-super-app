package com.tournament.billing.services;

import org.springframework.stereotype.Service;

@Service
public class PlanService {

    public boolean canCreateTournament(String tenantId) {
        // TODO: Implement logic to check tenant's current tournament count vs plan limit
        return true;
    }

    public boolean canAddJudge(String tenantId) {
        // TODO: Implement logic to check tenant's current judge count vs plan limit
        return true;
    }
}
