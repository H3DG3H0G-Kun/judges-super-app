package com.tournament.scoring.controller;

import com.tournament.scoring.dtos.ScoreSummaryDTO;
import com.tournament.scoring.services.ScoreAggregationService;
import com.tournament.tenant.TenantContextHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/scoring")
@RequiredArgsConstructor
public class ScoreSummaryController {

    private final ScoreAggregationService scoreAggregationService;

    @GetMapping("/summary")
    public ScoreSummaryDTO summarizeRound(
            @RequestParam Long sportsmanId,
            @RequestParam Integer round
    ) {
        return scoreAggregationService.summarizeRound(
                TenantContextHolder.getTenantId(),
                sportsmanId,
                round
        );
    }
}
