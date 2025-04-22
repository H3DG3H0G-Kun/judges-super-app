package com.tournament.management.repositories;

import com.tournament.management.entities.RuleConfig;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RuleConfigRepository extends JpaRepository<RuleConfig, Long> {
    List<RuleConfig> findByTournamentId(Long tournamentId);
}
