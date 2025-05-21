package com.eliska.cattoprojectapi.repository;

import com.eliska.cattoprojectapi.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsername(final String username);
}
