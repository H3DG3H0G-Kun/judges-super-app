package com.tournament.management.entities;

import com.tournament.common.enums.SportType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "tournaments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tournament {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String tenantId;

    private String name;
    private LocalDate startDate;
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    private SportType sportType;

    private Boolean usesJudgePanel;

    @OneToMany(mappedBy = "tournament", cascade = CascadeType.ALL)
    private List<Division> divisions;

    @ManyToMany
    @JoinTable(
            name = "tournament_rule_sets",
            joinColumns = @JoinColumn(name = "tournament_id"),
            inverseJoinColumns = @JoinColumn(name = "rule_set_id")
    )
    private List<RuleSet> ruleSets;

    @OneToOne(mappedBy = "tournament", cascade = CascadeType.ALL)
    private JudgePanelConfig judgePanel;
}
