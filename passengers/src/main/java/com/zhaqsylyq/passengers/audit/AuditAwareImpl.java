package com.zhaqsylyq.passengers.audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("auditAwareImpl")
public class AuditAwareImpl implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        // can integrate with SecurityContextHolder to fetch the current user
        return Optional.of("PASSENGERS_MICROSERVICE"); // Deafault for now
    }
}
