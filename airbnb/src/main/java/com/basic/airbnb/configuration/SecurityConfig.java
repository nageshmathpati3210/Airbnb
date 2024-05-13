package com.basic.airbnb.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;

@Configuration
public class SecurityConfig
{

    @Autowired
    private JWTRequestFilter jwtRequestFilter;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception
    {
        http.csrf().disable().cors().disable();
        http.addFilterBefore(jwtRequestFilter, AuthorizationFilter.class);
        http.authorizeHttpRequests().anyRequest().permitAll();
        /*        .requestMatchers("/api/v1/users/adduser","/api/v1/users/login")
                .permitAll()
                .requestMatchers("/api/v1/city/create").hasRole("ADMIN")
                .requestMatchers("/api/v2/users/city").hasAnyRole("ADMIN","USER")
                .anyRequest().authenticated(); */

        return http.build();

    }
}
