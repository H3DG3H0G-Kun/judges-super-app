package com.tournament.audit.services;

import com.tournament.audit.entities.AuditLog;
import com.tournament.audit.repositories.AuditLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuditService {

    private final AuditLogRepository auditLogRepository;

    public void logChange(String entityName, Long entityId, String action, String changedBy, String details) {
        AuditLog log = new AuditLog();
        log.setEntityName(entityName);
        log.setEntityId(entityId);
        log.setAction(action);
        log.setChangedBy(changedBy);
        log.setChangedAt(LocalDateTime.now());
        log.setDetails(details);
        auditLogRepository.save(log);
    }
}
