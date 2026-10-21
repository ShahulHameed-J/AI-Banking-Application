package Banking_app.Banking_backend.security;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            // Allow React frontend to communicate with Spring Boot
            .cors(cors -> {})

            // Disable CSRF for our REST API
            .csrf(csrf -> csrf.disable())

            // JWT authentication is stateless
            .sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )

            // API authorization rules
            .authorizeHttpRequests(auth -> auth

                // Login and Register don't require JWT
                .requestMatchers("/api/auth/**").permitAll()

                // Everything else requires authentication
                .anyRequest().authenticated()
            )

            // Run our JWT filter before Spring's username/password filter
            .addFilterBefore(
                jwtAuthenticationFilter,
                UsernamePasswordAuthenticationFilter.class
            );

        return http.build();
    }


    @Bean
    public CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration configuration = new CorsConfiguration();

        // React frontend
        configuration.setAllowedOrigins(
            List.of("http://localhost:5173")
        );

        // HTTP methods our frontend can use
        configuration.setAllowedMethods(
            List.of("GET", "POST", "PUT", "DELETE", "OPTIONS")
        );

        // Allow request headers such as Authorization
        configuration.setAllowedHeaders(
            List.of("*")
        );

        UrlBasedCorsConfigurationSource source =
            new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}