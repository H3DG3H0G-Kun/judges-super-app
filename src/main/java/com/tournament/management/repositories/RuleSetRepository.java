package com.tournament.management.repositories;

import com.tournament.management.entities.RuleSet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RuleSetRepository extends JpaRepository<RuleSet, Long> {
}
