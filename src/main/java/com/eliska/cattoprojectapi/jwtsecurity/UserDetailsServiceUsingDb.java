package com.eliska.cattoprojectapi.jwtsecurity;

import com.eliska.cattoprojectapi.model.UserEntity;
import com.eliska.cattoprojectapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.HttpStatusCodeException;


@Service
@RequiredArgsConstructor
public class UserDetailsServiceUsingDb implements UserDetailsService {
    private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        logger.info("----------------loadUserByUsername() called");
        final UserEntity userEntity = this.userRepository.findByUsername(username);
        if(userEntity == null) {
            throw new UsernameNotFoundException("Unknown user "+ username);
        }
        return User.withUsername(userEntity.getUsername())
                .password(userEntity.getPassword())
                .authorities("ROLE_USER")
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }

}