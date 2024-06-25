package com.io.java.events.managers.application.config;

import com.io.java.events.managers.application.utils.URLS;
import com.io.java.events.managers.security.filter.Filter;
import com.io.java.events.managers.security.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@SuppressWarnings("unused")
public class SecurityConfigTest {

    private final JwtUtil jwtUtil;

    @Bean
    @SneakyThrows(Exception.class)
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) {
        httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request -> {
                    URLS.getRhRoutes().forEach(URL -> request.requestMatchers(URL.getHttpMethod(), URL.getName()).permitAll());
                    URLS.getPublicRoutes().forEach(URL -> request.requestMatchers(URL.getHttpMethod(), URL.getName()).permitAll());
                    request.anyRequest().permitAll();
                })
                .addFilterBefore(new Filter(jwtUtil), UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }
}