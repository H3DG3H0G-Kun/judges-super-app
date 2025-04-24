package com.tournament.management.repositories;

import com.tournament.management.entities.RuleConfig;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RuleConfigRepository extends JpaRepository<RuleConfig, Long> {
}
