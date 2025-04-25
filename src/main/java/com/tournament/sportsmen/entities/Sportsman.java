package com.tournament.sportsmen.entities;

import com.tournament.common.enums.SportsmanRegistrationStatus;
import com.tournament.common.tenancy.TenantScopedEntity;
import com.tournament.management.entities.Division;
import com.tournament.management.entities.Tournament;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "sportsmen")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sportsman extends TenantScopedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String tenantId;

    private String fullName;
    private Integer age;
    private String gender;
    private String club;
    private String country;

    @Enumerated(EnumType.STRING)
    private SportsmanRegistrationStatus status = SportsmanRegistrationStatus.PENDING;

    private String rejectionComment;

    @ManyToOne
    @JoinColumn(name = "tournament_id", nullable = false)
    private Tournament tournament;

    @ManyToOne
    @JoinColumn(name = "division_id", nullable = false)
    private Division division;
}
