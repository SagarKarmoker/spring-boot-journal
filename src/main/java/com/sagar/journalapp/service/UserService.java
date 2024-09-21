package com.sagar.journalapp.service;

import com.sagar.journalapp.entity.User;
import com.sagar.journalapp.repo.UserRepo;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private UserRepo userRepository;

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(ObjectId id) {
        return userRepository.findById(String.valueOf(id));
    }

    public void deleteUser(ObjectId id) {
        userRepository.deleteById(String.valueOf(id));
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
