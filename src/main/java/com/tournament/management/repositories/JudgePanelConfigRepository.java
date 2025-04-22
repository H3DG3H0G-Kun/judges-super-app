package com.tournament.management.repositories;

import com.tournament.management.entities.JudgePanelConfig;
import com.tournament.management.entities.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JudgePanelConfigRepository extends JpaRepository<JudgePanelConfig, Long> {
    Optional<JudgePanelConfig> findByTournament(Tournament tournament);
}
