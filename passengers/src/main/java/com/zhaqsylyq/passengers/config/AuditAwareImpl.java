package com.zhaqsylyq.passengers.config;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditAwareImpl implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        // can integrate with SecurityContextHolder to fetch the current user
        return Optional.of("zhaqsylyq"); // Deafault for now
    }
}
