package com.sagar.journalapp.entity;

import lombok.Data;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "users")
@Data
public class User {
    @Id
    private ObjectId id;
    @Indexed(unique = true) // unique username
    @NonNull
    private String username;
    @NonNull
    private String email;
    @NonNull
    private String password;

    // user 1 -> * journals
    @DBRef // journal reference (only object ID) linking the journal [similar to foreign key]
    private List<Journal> journals = new ArrayList<>();
}
