package com.tournament.management.entities;

import com.tournament.common.tenancy.TenantScopedEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "rule_configs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RuleConfig extends TenantScopedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ruleKey;
    private String ruleLabel;
    private Integer maxScore;
    private Boolean required;
    private Integer round;

    @ManyToOne
    @JoinColumn(name = "rule_set_id")
    private RuleSet ruleSet;
}

