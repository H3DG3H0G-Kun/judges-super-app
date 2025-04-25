package com.tournament.management.entities;

import com.tournament.common.tenancy.TenantListener;
import com.tournament.common.tenancy.TenantScopedEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "rule_sets")
@EntityListeners(TenantListener.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RuleSet extends TenantScopedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @OneToMany(mappedBy = "ruleSet", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RuleConfig> ruleConfigs;
}
