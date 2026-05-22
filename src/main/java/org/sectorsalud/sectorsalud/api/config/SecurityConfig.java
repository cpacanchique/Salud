package org.sectorsalud.sectorsalud.api.config;

import org.sectorsalud.sectorsalud.api.security.JwtFilter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.http.HttpMethod;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.web.SecurityFilterChain;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http

                // HABILITAR CORS
                .cors(cors -> {})

                // DESACTIVAR CSRF
                .csrf(csrf -> csrf.disable())

                // SIN SESIONES
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                // AUTORIZACIÓN
                .authorizeHttpRequests(auth -> auth

                        // PERMITIR OPTIONS (CORS PREFLIGHT)
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                        // ENDPOINTS PUBLICOS
                        .requestMatchers("/auth/**").permitAll()
                        .requestMatchers("/patients/register").permitAll()

                        // TODO LO DEMÁS REQUIERE JWT
                        .anyRequest().authenticated()
                )

                // JWT FILTER
                .addFilterBefore(
                        jwtFilter,
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }
}
