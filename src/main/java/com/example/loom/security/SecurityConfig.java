package com.example.loom.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.loom.service.CustomUserDetailsService;
import com.example.loom.util.JwtFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtFilter jwtFilter;
    private final CustomUserDetailsService userDetailsService;

    public SecurityConfig(JwtFilter jwtFilter, CustomUserDetailsService userDetailsService) {
        this.jwtFilter = jwtFilter;
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    
    @Bean
    public AuthenticationManager authenticationManager( AuthenticationConfiguration config) throws Exception {

        return config.getAuthenticationManager();
    }

   
    @Bean
    public AuthenticationProvider authenticationProvider() {

        DaoAuthenticationProvider provider =new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());

        return provider;
    }

    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception {

        return http.csrf(csrf -> csrf.disable())
    

                .sessionManagement(session ->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth                   
                        .requestMatchers("/api/auth/register","/api/auth/login").permitAll()
                        .requestMatchers("/api/auth/get/**")
                        .hasAnyRole("ACADEMIC_ADMIN","SUPPORT_AGENT")
                        .requestMatchers("/api/auth/put/**")
                        .hasAnyRole("ACADEMIC_ADMIN","SUPPORT_AGENT")
                        .requestMatchers("/api/auth/delete/**")
                        .hasAnyRole("ACADEMIC_ADMIN","SUPPORT_AGENT")
                        .requestMatchers("/api/auth/patch/**")
                        .hasAnyRole("ACADEMIC_ADMIN","SUPPORT_AGENT")

                        .requestMatchers("/api/enrollments/post")
                        .hasAnyRole("LEARNER","ACADEMIC_ADMIN")
                        .requestMatchers("/api/enrollments/get")
                        .hasAnyRole("MENTOR","ACADEMIC_ADMIN","SUPPORT_AGENT")
                        .requestMatchers("/api/enrollments/get/**")
                        .hasAnyRole("LEARNER","MENTOR","ACADEMIC_ADMIN","SUPPORT_AGENT")
                        .requestMatchers("/api/enrollments/put/**")
                        .hasRole("ACADEMIC_ADMIN")
                        .requestMatchers("/api/enrollments/patch/**")
                        .hasRole("ACADEMIC_ADMIN")
                        .requestMatchers("/api/enrollments/delete/**")
                        .hasRole("ACADEMIC_ADMIN")

                        .requestMatchers("/feedback/post")
                        .hasRole("LEARNER")
                        .requestMatchers("/feedback/put/**","/feedback/patch/**")
                        .hasAnyRole("LEARNER","ACADEMIC_ADMIN")
                        .requestMatchers("/feedback/delete/**")
                        .hasRole("ACADEMIC_ADMIN")
                        .requestMatchers("/feedback/getFeedback","/feedback/get/**")
                        .hasAnyRole("MENTOR","ACADEMIC_ADMIN","SUPPORT_AGENT")

                        .requestMatchers("/api/sessions/post")
                        .hasAnyRole("MENTOR","ACADEMIC_ADMIN")
                        .requestMatchers("/api/sessions/put/**","/api/sessions/patch/**","/api/sessions/delete/**")
                        .hasAnyRole("MENTOR","ACADEMIC_ADMIN")
                        .requestMatchers("/api/sessions/get","/api/sessions/get/**")
                        .hasAnyRole("LEARNER","MENTOR","ACADEMIC_ADMIN","SUPPORT_AGENT")

                        .requestMatchers("/api/subjects/create")
                        .hasRole("ACADEMIC_ADMIN")
                        .requestMatchers("/api/subjects/update/**","/api/subjects/delete/**","/api/subjects/patch/**")
                        .hasRole("ACADEMIC_ADMIN")
                        .requestMatchers("/api/subjects/all","/api/subjects/get/**")
                        .hasAnyRole("ACADEMIC_ADMIN","MENTOR","LEARNER","SUPPORT_AGENT")
                        .anyRequest()
                        .authenticated())

                .httpBasic(httpBasic -> httpBasic.disable())
                .formLogin(form -> form.disable())
                .addFilterBefore(jwtFilter,UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}