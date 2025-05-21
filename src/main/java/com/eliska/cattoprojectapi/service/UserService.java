package com.eliska.cattoprojectapi.service;

import com.eliska.cattoprojectapi.CattoProjectApiApplication;
import com.eliska.cattoprojectapi.model.UserEntity;
import com.eliska.cattoprojectapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    public UserEntity getUserByUsername(final String username) {
        return userRepository.findByUsername(username);
    }

    public UserEntity createUserFromUserEntity(UserEntity userEntity) {

        if(userRepository.findByUsername(userEntity.getUsername()) != null) {
           throw  new RuntimeException("Username already exists");
        }

        return userRepository.save(userEntity

        );
    }

    public UserEntity updateUserFromUserEntity(UserEntity userEntity) {
        CattoProjectApiApplication.MyLog("updateUserFromUserEntity called");
        CattoProjectApiApplication.MyLog(userEntity.getFname());
        return userRepository.save(userEntity);
    }

}
