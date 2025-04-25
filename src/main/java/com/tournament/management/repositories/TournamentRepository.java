package com.tournament.management.repositories;

import com.tournament.management.entities.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TournamentRepository extends JpaRepository<Tournament, Long> {

    Optional<Tournament> findByIdAndTenantId(Long id, String tenantId);

    List<Tournament> findAllByTenantId(String tenantId);

    void deleteByIdAndTenantId(Long id, String tenantId);
}
