package com.tournament.judges.repositories;

import com.tournament.judges.entities.JudgeAssignment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JudgeAssignmentRepository extends JpaRepository<JudgeAssignment, Long> {
}
