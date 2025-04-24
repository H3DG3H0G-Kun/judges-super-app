package com.tournament.management.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateRuleSetRequest {
    private String name;
    private String description;
    private List<RuleConfigRequest> rules;
}
