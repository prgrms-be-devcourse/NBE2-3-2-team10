package org.team10.washcode.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.team10.washcode.jwt.JwtAuthenticationFilter;
import org.team10.washcode.jwt.JwtProvider;

import java.io.IOException;
import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtProvider jwtProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return
                http
                        .csrf(AbstractHttpConfigurer::disable)
                        .formLogin(AbstractHttpConfigurer::disable)
                        .httpBasic(AbstractHttpConfigurer::disable)
                        .cors(AbstractHttpConfigurer::disable)
                        .headers(header -> header.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                        .authorizeHttpRequests((auth) -> auth
                                .requestMatchers("/WEB-INF/view/**","/upload/**","/error","/swagger-ui/**", "/v3/api-docs/**").permitAll()
                                .requestMatchers("/","/register","/api/user/login","/login").permitAll()
                                .requestMatchers("/main").permitAll()
                                .requestMatchers("/api/user/address").permitAll()
                                .dispatcherTypeMatchers(jakarta.servlet.DispatcherType.FORWARD).permitAll()
                                .dispatcherTypeMatchers(jakarta.servlet.DispatcherType.INCLUDE).permitAll()
                                .anyRequest().permitAll()
                        )
                        //.addFilterBefore(new JwtAuthenticationFilter(jwtProvider), UsernamePasswordAuthenticationFilter.class)
                        .build();
    }
}
