package com.sagar.journalapp.controller;


import com.sagar.journalapp.entity.User;
import com.sagar.journalapp.service.UserService;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user/")
public class UserController {

    // logger
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @PostMapping("add-user")
    public ResponseEntity<?> createUser(@RequestBody User user){
        try{
           userService.crateUser(user);
           return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (Exception e){
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PostMapping("get-all-users")
    public ResponseEntity<?> getAllUser(){
        try{
            List<User> listOfUsers = userService.getAllUsers();
            return new ResponseEntity<>(listOfUsers, HttpStatus.OK);
        }catch (Exception e){
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getUserById(@PathVariable ObjectId id){
        try{
            Optional<User> user = userService.getUserById(id);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }catch (Exception e){
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable ObjectId id){
        try{
            userService.deleteUser(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PutMapping("{username}")
    public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable String username){
        try{
            User existingUser = userService.findByUsername(username);

            if(existingUser != null){
                existingUser.setPassword(user.getPassword());
                userService.crateUser(existingUser);
            }

            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

}
