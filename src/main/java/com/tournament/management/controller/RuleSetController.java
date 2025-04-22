package com.tournament.management.controller;

import com.tournament.management.dtos.CreateRuleSetRequest;
import com.tournament.management.dtos.RuleSetResponse;
import com.tournament.management.services.RuleSetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rule-sets")
@RequiredArgsConstructor
public class RuleSetController {

    private final RuleSetService service;

    @PostMapping
    public ResponseEntity<RuleSetResponse> create(@RequestBody CreateRuleSetRequest request) {
        return ResponseEntity.ok(service.save(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RuleSetResponse> get(@PathVariable Long id) {
        return ResponseEntity.ok(service.get(id));
    }

    @GetMapping
    public ResponseEntity<List<RuleSetResponse>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }
}

