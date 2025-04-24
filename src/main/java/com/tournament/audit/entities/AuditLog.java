package com.tournament.audit.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "audit_logs")
@Data
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String entityName;
    private Long entityId;
    private String action; // CREATE, UPDATE, DELETE
    private String changedBy;
    private LocalDateTime changedAt;

    @Column(columnDefinition = "TEXT")
    private String details; // JSON or text describing changes
}
