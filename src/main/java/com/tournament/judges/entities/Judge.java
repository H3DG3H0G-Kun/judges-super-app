package com.tournament.judges.entities;

import com.tournament.management.entities.JudgePanelConfig;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "judges")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Judge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String role; // Must match JudgePanelConfig.roles

    @ManyToOne
    @JoinColumn(name = "panel_id")
    private JudgePanelConfig panel;
}
