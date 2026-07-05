package com.cognizant.spring_learn.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

//    @Bean
//    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//
//        http
//                .authorizeHttpRequests(auth -> auth
//                        .anyRequest().authenticated())
//                .httpBasic(Customizer.withDefaults());
//
//        return http.build();
//    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    InMemoryUserDetailsManager users(PasswordEncoder encoder) {

        UserDetails admin =
                User.withUsername("admin")
                        .password(encoder.encode("pwd"))
                        .roles("ADMIN")
                        .build();

        UserDetails user =
                User.withUsername("user")
                        .password(encoder.encode("pwd"))
                        .roles("USER")
                        .build();

        return new InMemoryUserDetailsManager(admin, user);
    }

//    @Bean
//    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//
//        http
//                .csrf(csrf -> csrf.disable())
//                .httpBasic(Customizer.withDefaults())
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/countries").hasRole("USER")
//                        .anyRequest().authenticated());
//
//        return http.build();
//    }

    // JWT TOKEN
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//
//        http
//                .csrf(csrf -> csrf.disable())
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/authenticate").authenticated()
//                        .anyRequest().authenticated()
//                )
//                .httpBasic(Customizer.withDefaults());
//
//        return http.build();
//    }

    // User defined details
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//
//        http
//                .csrf(csrf -> csrf.disable())
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/countries").hasRole("USER")
//                        .requestMatchers("/authenticate").hasAnyRole("USER", "ADMIN")
//                        .anyRequest().authenticated()
//                )
//                .httpBasic(Customizer.withDefaults());
//
//        return http.build();
//    }

    // Final Authorize based on jwt

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/authenticate").permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(new JwtAuthorizationFilter(),
                        UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }


}