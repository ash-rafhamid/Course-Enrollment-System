package com.example.course_enrollment_system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // 1. Disable CSRF (To allow POST requests via Postman easily for now)
                .csrf(csrf -> csrf.disable())

                // 2. Define URL Rules
                .authorizeHttpRequests(auth -> auth
                        // Allow anyone to see the API documentation (Swagger/OpenAPI)
                        .requestMatchers("/v3/api-docs/**", "/swagger-ui/**").permitAll()

                        // ADMIN RULES: Only Admins can Create (POST)
                        .requestMatchers(HttpMethod.POST, "/courses/**").hasRole("STUDENT")
                        .requestMatchers(HttpMethod.POST, "/students/**").hasRole("STUDENT")
                        .requestMatchers(HttpMethod.POST, "/enrollments/**").hasRole("STUDENT")

                        // GENERAL RULES: Students & Admins can View (GET)
                        .requestMatchers(HttpMethod.GET, "/**").hasAnyRole("ADMIN", "STUDENT")

                        // Lock everything else
                        .anyRequest().authenticated()
                )

                // 3. Enable Basic Auth (The Browser Popup)
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
        // Create an ADMIN user
        UserDetails admin = User.builder()
                .username("admin")
                .password(encoder.encode("admin123")) // Password is "admin123"
                .roles("ADMIN")
                .build();

        // Create a STUDENT user
        UserDetails student = User.builder()
                .username("std")
                .password(encoder.encode("std")) // Password is "student123"
                .roles("STUDENT")
                .build();

        return new InMemoryUserDetailsManager(admin, student);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}