
package com.tournament.common.tenancy;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public abstract class TenantScopedEntity {
    
    @Column(name = "tenant_id", nullable = false)
    private String tenantId;
}
