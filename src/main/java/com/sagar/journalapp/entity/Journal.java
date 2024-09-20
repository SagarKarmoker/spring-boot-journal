package com.sagar.journalapp.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collation = "journal")
@Setter
@Getter
public class Journal {
    @Id
    private String id;
    private String title;
    private String description;
    private Date publishedDate;
}
