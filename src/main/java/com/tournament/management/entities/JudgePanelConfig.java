package com.tournament.management.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "judge_panel_configs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JudgePanelConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer totalJudges;
    private Boolean allowOverride;
    private Boolean anonymousScoring;

    @ElementCollection
    @CollectionTable(name = "judge_roles", joinColumns = @JoinColumn(name = "panel_id"))
    @Column(name = "role")
    private List<String> judgeRoles;

    @OneToOne
    @JoinColumn(name = "tournament_id")
    private Tournament tournament;
}
