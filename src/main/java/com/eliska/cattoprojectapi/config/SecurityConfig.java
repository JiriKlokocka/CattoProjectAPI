package com.eliska.cattoprojectapi.config;

import com.eliska.cattoprojectapi.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import static org.springframework.security.config.Customizer.withDefaults;


@Configuration // Marks this class as a configuration class for Spring.
@EnableWebSecurity // Enables web security for the application.
public class SecurityConfig  {
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean // Indicates that this method returns a Spring bean.
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests(authorizeRequests ->
//                authorizeRequests
//                        .requestMatchers("/h2-console/**").permitAll()
//                        .requestMatchers("api/users/**").permitAll()
//                        .requestMatchers("/api/users/createuser/**").permitAll()
//                        .anyRequest().authenticated());

        http.authorizeHttpRequests(auth -> {
            auth.requestMatchers("/h2-console/**").permitAll();
            auth.requestMatchers("/api/users/createuser/**").permitAll();
            auth.requestMatchers("/api/users/createuser").permitAll();
            auth.requestMatchers("/error**").permitAll();
            auth.anyRequest().authenticated();
        });

        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        //http.exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler));

        http.httpBasic(withDefaults()); // Enables HTTP Basic Authentication with default settings.        //http.httpBasic(withDefaults());
        //http.formLogin(withDefaults());
        http.logout(withDefaults());

        http.headers(headers -> headers.frameOptions(frameOptions -> frameOptions.sameOrigin()));
        http.csrf(csrf -> csrf.disable());
        //http.addFilterBefore(authenticationJwtTokenFilter(),UsernamePasswordAuthenticationFilter.class);
        return http.build();
//        http
//                .csrf(csrf -> csrf.disable()) // Disables CSRF protection, common in stateless REST APIs.
//                .authorizeRequests(authorize -> authorize
//                        .requestMatchers(new AntPathRequestMatcher("/user", "POST")).permitAll() // Allow POST requests to /user without authentication
//                        .anyRequest().authenticated() // Ensures all requests are authenticated.
//                )
//                .httpBasic(withDefaults()) // Enables HTTP Basic Authentication with default settings.
//                .sessionManagement(session -> session
//                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)); // Configures session management to be stateless.
//        return http.build(); // Builds and returns the SecurityFilterChain.
    }

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new CustomPasswordEncoder();
    }
}