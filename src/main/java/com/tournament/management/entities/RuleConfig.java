package com.tournament.management.entities;

import com.tournament.common.tenancy.TenantListener;
import com.tournament.common.tenancy.TenantScopedEntity;
import com.tournament.management.enums.FormulaType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "rule_configs")
@EntityListeners(TenantListener.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RuleConfig extends TenantScopedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ruleKey;
    private String ruleLabel;
    private Double maxScore;
    private Double minScore;
    private Double weight;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FormulaType formulaType;

    @ManyToOne
    @JoinColumn(name = "rule_set_id")
    private RuleSet ruleSet;
}
