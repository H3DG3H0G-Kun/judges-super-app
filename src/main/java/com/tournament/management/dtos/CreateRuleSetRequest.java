package com.tournament.management.dtos;

import lombok.Data;

import java.util.List;

@Data
public class CreateRuleSetRequest {
    private String name;
    private String description;
    private List<RuleConfigRequest> rules;
}
