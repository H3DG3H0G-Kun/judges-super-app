package com.tournament.sportsmen.repositories;

import com.tournament.sportsmen.entities.Sportsman;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SportsmanRepository extends JpaRepository<Sportsman, Long> {

    List<Sportsman> findByTournamentIdAndTenantId(Long tournamentId, String tenantId);

    Optional<Sportsman> findByIdAndTenantId(Long id, String tenantId);

    List<Sportsman> findByStatusAndTenantId(String status, String tenantId);
}
