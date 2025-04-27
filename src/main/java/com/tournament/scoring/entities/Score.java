package com.tournament.scoring.entities;

import com.tournament.common.tenancy.TenantScopedEntity;
import com.tournament.management.entities.RuleConfig;
import com.tournament.sportsmen.entities.Sportsman;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "scores")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Score extends TenantScopedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sportsman_id", nullable = false)
    private Sportsman sportsman;

    @ManyToOne
    @JoinColumn(name = "rule_id", nullable = false)
    private RuleConfig rule;

    @Column(nullable = false)
    private Double value;

    @Column(nullable = false)
    private String judgeName;

    private Integer round = 1;
}
