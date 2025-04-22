package com.tournament.management.repositories;

import com.tournament.management.entities.Division;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DivisionRepository extends JpaRepository<Division, Long> {
    List<Division> findByTournamentId(Long tournamentId);
}
