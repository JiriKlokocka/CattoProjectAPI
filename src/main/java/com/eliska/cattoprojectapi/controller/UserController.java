package com.eliska.cattoprojectapi.controller;

import com.eliska.cattoprojectapi.CattoProjectApiApplication;
import com.eliska.cattoprojectapi.model.UserEntity;
import com.eliska.cattoprojectapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/testcredentials")
    public ResponseEntity testCredentials() {
        return ResponseEntity.status(HttpStatus.OK).body("User is authenticated");
    }


    @GetMapping("/getall")
    public List<UserEntity> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/getbyusername/{username}")
    public UserEntity getUserByUsername(@PathVariable(required = true) String username) {
        return userService.getUserByUsername(username);
    }

//    @PostMapping("/createuser")
//    public UserEntity createUser(@RequestParam String username, @RequestParam String password, @RequestParam String email, @RequestParam String firstname, @RequestParam String lastname) {
//        return userService.createUser(username, password, email, firstname, lastname);
//    }

    @PostMapping("/createuser")
    @ResponseBody
    public ResponseEntity createUser(RequestEntity<UserEntity> requestEntity)
    {
        CattoProjectApiApplication.MyLog("createuser endpoint called");
        try {
            UserEntity userEntity = userService.createUserFromUserEntity(requestEntity.getBody());
            return ResponseEntity.status(HttpStatus.CREATED).body(userEntity);
        } catch (Exception e) {
            CattoProjectApiApplication.MyLog(e.getMessage());
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/updateuser")
    public ResponseEntity updateUser(@RequestBody UserEntity userEntity) {
        CattoProjectApiApplication.MyLog("updateuser endpoint called");
        CattoProjectApiApplication.MyLog("---" + userEntity.getId().toString());
         try {

             UserEntity userEntityUpdated = userService.updateUserFromUserEntity(userEntity);
             return ResponseEntity.status(HttpStatus.OK).body(userEntityUpdated);
         } catch (Exception e) {
             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
         }
    }

//    public UserEntity createUser(@RequestBody UserEntity user) {}
}
