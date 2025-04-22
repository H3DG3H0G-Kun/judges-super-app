package com.tournament.management.dtos;

import lombok.Data;

import java.util.List;

@Data
public class RuleSetResponse {
    private Long id;
    private String name;
    private String description;
    private List<RuleConfigResponse> rules;
}
