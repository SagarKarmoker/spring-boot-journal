package com.sagar.journalapp.repo;

import com.sagar.journalapp.entity.Journal;
import org.springframework.data.mongodb.repository.MongoRepository;

//object type, id type
public interface JournalEntryRepo extends MongoRepository<Journal, String> {

}
