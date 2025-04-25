package com.tournament.management.entities;

import com.tournament.common.enums.SportType;
import com.tournament.common.tenancy.TenantListener;
import com.tournament.common.tenancy.TenantScopedEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "tournaments")
@EntityListeners(TenantListener.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tournament extends TenantScopedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private LocalDate startDate;
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    private SportType sportType;

    private Boolean usesJudgePanel;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tournament")
    private List<Division> divisions;

    @ManyToMany
    @JoinTable(
            name = "tournament_rule_sets",
            joinColumns = @JoinColumn(name = "tournament_id"),
            inverseJoinColumns = @JoinColumn(name = "rule_set_id")
    )
    private List<RuleSet> ruleSets;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "tournament")
    private JudgePanelConfig judgePanel;
}
