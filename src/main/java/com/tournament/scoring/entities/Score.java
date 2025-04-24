package com.tournament.scoring.entities;

import com.tournament.management.entities.RuleConfig;
import com.tournament.sportsmen.entities.Sportsman;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "scores")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Score {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String tenantId;

    @ManyToOne
    @JoinColumn(name = "sportsman_id", nullable = false)
    private Sportsman sportsman;

    @ManyToOne
    @JoinColumn(name = "rule_id", nullable = false)
    private RuleConfig rule;

    @Column(nullable = false)
    private Double value;

    @Column(nullable = false)
    private String judgeName; // Can be replaced with judge ID/user later

    private Integer round = 1; // Optional support for multi-round scoring
}
