package com.eliska.cattoprojectapi.service;

import com.eliska.cattoprojectapi.model.UserEntity;
import com.eliska.cattoprojectapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
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