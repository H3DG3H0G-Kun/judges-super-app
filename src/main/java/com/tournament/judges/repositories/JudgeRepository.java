package com.tournament.judges.repositories;

import com.tournament.judges.entities.Judge;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JudgeRepository extends JpaRepository<Judge, Long> {
    List<Judge> findByPanel_Tournament_Id(Long tournamentId);
}
