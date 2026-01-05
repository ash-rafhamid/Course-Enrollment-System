package com.example.course_enrollment_system.config;

import com.example.course_enrollment_system.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;

    public SecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Allow POST for testing
                .authorizeHttpRequests(auth -> auth
                        // Public access for Swagger
                        .requestMatchers("/v3/api-docs/**", "/swagger-ui/**").permitAll()

                        // ADMIN ONLY: Add Courses, Students, Enrollments
                        .requestMatchers(HttpMethod.POST, "/**").hasRole("ADMIN")

                        // EVERYONE (Admin + Student): Can View data
                        .requestMatchers(HttpMethod.GET, "/**").hasAnyRole("ADMIN", "STUDENT")

                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults()) // Use Browser Popup
                .authenticationProvider(authenticationProvider());

        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}