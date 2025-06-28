package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.demo.security.AuthenticationFIlter;

@Configuration
@Deprecated
public class SecurityConfig {

    @Autowired
    AuthenticationFIlter authenticationFIlter;

    @Autowired
    AuthenticationProvider authenticationProvider;

    @Autowired
    AuthEntryPoint authEntryPoint;

    private final static String[] WHITE_LIST_URL = { "/login" };

    @Bean
    SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        // Disable CSRF
        httpSecurity.csrf(csrf -> csrf.disable());
        // Change session management Policy
        httpSecurity.sessionManagement(
                sessionConfig -> sessionConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        // Regsiter Custom Filter
        httpSecurity.addFilterBefore(authenticationFIlter, UsernamePasswordAuthenticationFilter.class);
        // Step 4. Register
        // Public Handler
        httpSecurity.authenticationProvider(authenticationProvider);

        // Protected Resources
        httpSecurity.authorizeHttpRequests(
                httpConfig -> httpConfig.requestMatchers(WHITE_LIST_URL).permitAll().requestMatchers("/api/**")
                        .hasAnyAuthority("USER").requestMatchers(HttpMethod.POST, "/leave/user").hasAnyAuthority("USER")
                        .requestMatchers(HttpMethod.GET, "/leave/user").hasAnyAuthority("ADMIN", "USER")
                        .requestMatchers(HttpMethod.PUT, "/leave/comment").hasAnyAuthority("USER")
                        .requestMatchers(HttpMethod.PUT, "/leave/status").hasAnyAuthority("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/leave").hasAnyAuthority("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/leave/days").hasAnyAuthority("ADMIN")
                        .anyRequest().authenticated());

        // add exception handler
        httpSecurity.exceptionHandling(exceptionConfig -> exceptionConfig.authenticationEntryPoint(authEntryPoint));

        return httpSecurity.build();
    }

}
