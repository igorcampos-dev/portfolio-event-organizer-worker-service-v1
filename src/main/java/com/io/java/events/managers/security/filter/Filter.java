package com.io.java.events.managers.security.filter;

import com.io.java.events.managers.application.controller.config.exception.ErrorResponse;
import com.io.java.events.managers.application.utils.URLS;
import com.io.java.events.managers.security.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
@RequiredArgsConstructor
public class Filter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(@Nullable HttpServletRequest request,
                                    @Nullable HttpServletResponse response,
                                    @Nullable FilterChain filterChain) {

        assert request != null;
        assert filterChain != null;
        assert response != null;

        try {
            String jwt = request.getHeader("Authorization");
            jwtUtil.authenticate(jwt);
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            ErrorResponse.getError(response, e);
        }
    }

    @Override
    protected boolean shouldNotFilter(@Nullable HttpServletRequest request) {
        assert request != null;
        return URLS.getPublicRoutes()
                   .stream()
                   .anyMatch(route -> request.getServletPath().contains(route.getName()));
    }


}