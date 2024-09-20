package com.sagar.journalapp.entity;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collation = "journals")
@Setter
@Getter
public class Journal {
    @Id
    private ObjectId id; // mongo object
    private String title;
    private String description;
    private LocalDateTime publishedDate;
}
