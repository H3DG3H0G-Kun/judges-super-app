package com.tournament.sportsmen.repositories;

import com.tournament.common.enums.SportsmanRegistrationStatus;
import com.tournament.sportsmen.entities.Sportsman;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SportsmanRepository extends JpaRepository<Sportsman, Long> {
    List<Sportsman> findByTournamentId(Long tournamentId);
    List<Sportsman> findByStatus(SportsmanRegistrationStatus status);
}
