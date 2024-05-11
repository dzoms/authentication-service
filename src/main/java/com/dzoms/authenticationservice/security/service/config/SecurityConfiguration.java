package com.dzoms.authenticationservice.security.service.config;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.boot.autoconfigure.security.oauth2.resource.servlet.OAuth2ResourceServerAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    @Bean
    @SneakyThrows
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(registry -> registry
                        .anyRequest()
                        .authenticated())
                .oauth2ResourceServer((oauth2) -> oauth2
                        .jwt(Customizer.withDefaults()))
                .sessionManagement(configurer -> configurer
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }

}
