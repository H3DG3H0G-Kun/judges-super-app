package com.tournament.billing.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "plans")
@Data
public class Plan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer maxTournaments;

    private Integer maxJudges;

    // Add other limits as needed
}
