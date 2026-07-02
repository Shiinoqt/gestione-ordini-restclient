package com.its.gestioneordinirestclient.auth;



import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Optional;

@Component
public class AuditorAwareImpl implements AuditorAware<String> {

    private static final String DEFAULT_AUDITOR = "SYSTEM";

    @Override
    public Optional<String> getCurrentAuditor() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();

        if (!(requestAttributes instanceof ServletRequestAttributes servletAttributes)) {
            return Optional.of(DEFAULT_AUDITOR);
        }

        HttpServletRequest request = servletAttributes.getRequest();

        String userId = request.getHeader("X-User-Id");
        String email = request.getHeader("X-User-Email");
        String username = request.getHeader("X-User-Name");

        if (userId != null && !userId.isBlank()) {
            return Optional.of(userId);
        }

        if (email != null && !email.isBlank()) {
            return Optional.of(email);
        }

        if (username != null && !username.isBlank()) {
            return Optional.of(username);
        }

        return Optional.of(DEFAULT_AUDITOR);
    }
}
