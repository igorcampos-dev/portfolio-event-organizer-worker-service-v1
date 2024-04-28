package com.io.java.events.managers.security.filter;

import com.io.java.events.managers.application.utils.URLS;
import com.io.java.events.managers.security.model.response.ErrorResponse;
import com.io.java.events.managers.security.util.JwtUtil;
import jakarta.annotation.Nullable;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.util.Objects;

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

            if (jwt != null)
               authenticate(jwt);

          filterChain.doFilter(request, response);
          unauthorized(response);
        } catch (Exception e) {
            ErrorResponse.getError(response, e);
        }
    }

    private void authenticate(String jwt) {

        if (jwtUtil.isValidToken(jwt)) {
            var authentication = jwtUtil.authenticate(jwt);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
    }

    private void unauthorized(HttpServletResponse response) {
        if (response.getStatus() == HttpServletResponse.SC_UNAUTHORIZED) {
            ErrorResponse.getErrorUnauthorized(response);
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return URLS.getPublicRoutes().stream().anyMatch(url -> url.getName().contains(request.getServletPath()));
    }
}
