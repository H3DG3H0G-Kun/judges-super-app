package com.tournament.management.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "divisions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Division {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String tenantId;

    private String name;
    private String description;

    @ManyToOne
    @JoinColumn(name = "tournament_id")
    private Tournament tournament;
}
