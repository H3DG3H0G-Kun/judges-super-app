
package com.tournament.common.tenancy;

import com.tournament.tenant.TenantContextHolder;
import jakarta.persistence.PrePersist;
import org.springframework.stereotype.Component;

@Component
public class TenantListener {

    @PrePersist
    public void beforeInsert(Object entity) {
        if (entity instanceof TenantScopedEntity scoped) {
            scoped.setTenantId(TenantContextHolder.getTenantId());
        }
    }
}
