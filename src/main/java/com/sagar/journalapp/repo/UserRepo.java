package com.sagar.journalapp.repo;

import com.sagar.journalapp.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo extends MongoRepository<User, String> {
    User findByUsername(String username);
}
