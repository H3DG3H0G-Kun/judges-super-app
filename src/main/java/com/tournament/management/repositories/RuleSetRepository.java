package com.tournament.management.repositories;

import com.tournament.management.entities.RuleSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RuleSetRepository extends JpaRepository<RuleSet, Long> {

    Optional<RuleSet> findByIdAndTenantId(Long id, String tenantId);

    List<RuleSet> findAllByTenantId(String tenantId);
}
