package com.vsign.backend.common.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vsign.backend.common.exception.ApiErrorResponse;
import com.vsign.backend.common.exception.ErrorCode;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    public static final String AUTH_EMAIL_ATTRIBUTE = "authenticatedEmail";
    public static final String AUTH_ROLE_ATTRIBUTE = "authenticatedRole";
    private static final String BEARER_PREFIX = "Bearer ";

    private final JwtService jwtService;
    private final ObjectMapper objectMapper;

    public JwtAuthFilter(JwtService jwtService, ObjectMapper objectMapper) {
        this.jwtService = jwtService;
        this.objectMapper = objectMapper;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String path = request.getRequestURI();
        if (!(path.startsWith("/api/v1/me")
                || path.startsWith("/api/v1/admin")
                || path.startsWith("/api/v1/gamification")
                || path.startsWith("/api/v1/leaderboards"))) {
            filterChain.doFilter(request, response);
            return;
        }

        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authHeader == null || !authHeader.startsWith(BEARER_PREFIX)) {
            writeUnauthorized(response, request, "Missing or invalid Authorization header");
            return;
        }

        String token = authHeader.substring(BEARER_PREFIX.length());
        Optional<JwtService.Principal> principal = jwtService.extractPrincipalIfValid(token);
        if (principal.isEmpty()) {
            writeUnauthorized(response, request, "Invalid or expired token");
            return;
        }

        request.setAttribute(AUTH_EMAIL_ATTRIBUTE, principal.get().email());
        request.setAttribute(AUTH_ROLE_ATTRIBUTE, principal.get().role());
        filterChain.doFilter(request, response);
    }

    private void writeUnauthorized(HttpServletResponse response, HttpServletRequest request, String message) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        objectMapper.writeValue(
                response.getWriter(),
                ApiErrorResponse.of(ErrorCode.UNAUTHORIZED, message, request.getRequestURI())
        );
    }

    @Override
    protected boolean shouldNotFilterErrorDispatch() {
        return true;
    }

    @Override
    protected boolean shouldNotFilterAsyncDispatch() {
        return true;
    }
}
