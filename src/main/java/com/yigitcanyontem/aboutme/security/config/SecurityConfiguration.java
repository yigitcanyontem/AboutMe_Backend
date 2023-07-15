package com.yigitcanyontem.aboutme.security.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import static com.yigitcanyontem.aboutme.users.Role.*;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration {

  private final JwtAuthenticationFilter jwtAuthFilter;
  private final AuthenticationProvider authenticationProvider;
  private final LogoutHandler logoutHandler;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .csrf()
        .disable()
            .cors((cors) ->{
                    CorsConfiguration config = new CorsConfiguration();
                    config.addAllowedOrigin("http://localhost:3000");
                    config.addAllowedMethod("GET");
                    config.addAllowedMethod("POST");
                    config.addAllowedMethod("PUT");
                    config.addAllowedMethod("DELETE");
                    config.addAllowedMethod("PATCH");
                    config.addAllowedHeader("Authorization");
                    config.addAllowedHeader("Content-Type");
                    config.addAllowedHeader("Authorization");

                    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
                    source.registerCorsConfiguration("/api/v1/auth/authenticate", config);

                    UrlBasedCorsConfigurationSource source1 = new UrlBasedCorsConfigurationSource();
                    source1.registerCorsConfiguration("/api/v1/auth/register", config);

                    UrlBasedCorsConfigurationSource source2 = new UrlBasedCorsConfigurationSource();
                    source2.registerCorsConfiguration("/api/v1/auth/refresh-token", config);

                    cors.configurationSource(request -> config);
        })
        .authorizeHttpRequests()
        .requestMatchers(
                "/api/v1/auth/**",
                "/user/favmovie/**",
                "/user/favshows/**",
                "/user/favalbums/**",
                "/user/favbooks/**"

        )
        .permitAll()
        .requestMatchers(
                HttpMethod.GET,
                "/countries"
        ).permitAll()
        .requestMatchers("/movie/**").hasAnyRole(ADMIN.name(), MANAGER.name())
        .requestMatchers("/user/**").hasAnyRole(ADMIN.name(), MANAGER.name())
        .requestMatchers("/book/**").hasAnyRole(ADMIN.name(), MANAGER.name())
        .requestMatchers("/show/**").hasAnyRole(ADMIN.name(), MANAGER.name())
        .requestMatchers("/album/**").hasAnyRole(ADMIN.name(), MANAGER.name())
        .requestMatchers("/search/**").hasAnyRole(ADMIN.name(), MANAGER.name())

        .anyRequest()
          .authenticated()
        .and()
          .sessionManagement()
          .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .authenticationProvider(authenticationProvider)
        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
        .logout()
        .logoutUrl("/api/v1/auth/logout")
        .addLogoutHandler(logoutHandler)
        .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
    ;

    return http.build();
  }

}
