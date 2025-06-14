package com.invent.inventory.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class RestAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest req,
            HttpServletResponse resp,
            AccessDeniedException accessEx) throws IOException {
        resp.setContentType("application/json");
        resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
        resp.getWriter().write(
                "{\"error\":\"Forbidden\",\"message\":\"" + accessEx.getMessage() + "\"}");
    }
}
