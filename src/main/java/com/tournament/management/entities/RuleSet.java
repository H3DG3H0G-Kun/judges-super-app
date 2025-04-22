package com.tournament.management.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "rule_sets")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RuleSet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // e.g., "Artistic Gymnastics 2025"
    private String description;

    @OneToMany(mappedBy = "ruleSet", cascade = CascadeType.ALL)
    private List<RuleConfig> ruleConfigs;
}
