package com.tournament.scoring.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "judge_scores")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JudgeScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long tournamentId;
    private Long sportsmanId;
    private Long judgeId;

    private String judgeName;
    private Integer score;
    private Boolean finalized = false;
}

