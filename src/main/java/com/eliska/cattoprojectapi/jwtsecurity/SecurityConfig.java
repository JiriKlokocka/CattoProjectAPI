package com.eliska.cattoprojectapi.jwtsecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration // Marks this class as a configuration class for Spring.
@EnableWebSecurity // Enables web security for the application.
@EnableMethodSecurity
public class SecurityConfig  {
    //@Autowired
    //private UserDetailsService userDetailsService;

    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }


    @Bean // Indicates that this method returns a Spring bean.
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(authReq -> {
            authReq.requestMatchers("/h2-console/**").permitAll();
            authReq.requestMatchers("/signin/**").permitAll();
            authReq.requestMatchers("/api/users/createuser/**").permitAll();
            authReq.requestMatchers("/api/users/createuser").permitAll();
            authReq.requestMatchers("/error**").permitAll();
            authReq.anyRequest().authenticated();
        });
        http.exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler));
        // Configures session management to be stateless.
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        //


        // Enables HTTP Basic Authentication with default settings.
        //http.httpBasic(withDefaults());
        //http.formLogin(withDefaults());
        //http.logout(withDefaults());

        http.headers(headers -> headers.frameOptions(frameOptions -> frameOptions.sameOrigin()));
        http.csrf(csrf -> csrf.disable());
        http.addFilterBefore(authenticationJwtTokenFilter(),UsernamePasswordAuthenticationFilter.class);
        // Builds and returns the SecurityFilterChain.
        return http.build();
//                .authorizeRequests(authorize -> authorize
//                        .requestMatchers(new AntPathRequestMatcher("/user", "POST")).permitAll() // Allow POST requests to /user without authentication
//                        .anyRequest().authenticated() // Ensures all requests are authenticated.
//                )
//                .httpBasic(withDefaults()) // Enables HTTP Basic Authentication with default settings.
//                .sessionManagement(session -> session
//                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)); // Configures session management to be stateless.
//        return http.build(); // Builds and returns the SecurityFilterChain.
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new CustomPasswordEncoder();
        //return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
        return builder.getAuthenticationManager();
    }
}