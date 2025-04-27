package com.tournament.judges.entities;

import com.tournament.common.tenancy.TenantScopedEntity;
import com.tournament.management.entities.JudgePanelConfig;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "judges")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Judge extends TenantScopedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String tenantId;

    private String name;

    private String role;

    @ManyToOne
    @JoinColumn(name = "panel_id")
    private JudgePanelConfig panel;
}
