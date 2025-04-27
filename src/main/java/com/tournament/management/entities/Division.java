package com.tournament.management.entities;

import com.tournament.common.tenancy.TenantListener;
import com.tournament.common.tenancy.TenantScopedEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "divisions")
@EntityListeners(TenantListener.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Division extends TenantScopedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @ManyToOne
    @JoinColumn(name = "tournament_id")
    private Tournament tournament;
}
