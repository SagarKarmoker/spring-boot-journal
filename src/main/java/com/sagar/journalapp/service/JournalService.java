package com.sagar.journalapp.service;

import com.sagar.journalapp.entity.Journal;
import com.sagar.journalapp.repo.JournalEntryRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JournalService {
    // business logic here
    @Autowired
    private JournalEntryRepo journalEntryRepo;

    public void saveEntry(Journal journal){
        journalEntryRepo.save(journal);
    }

    public List<Journal> getAllJournals(){
        return journalEntryRepo.findAll();
    }

    public Optional<Journal> getJournalPostById(ObjectId id){
        return journalEntryRepo.findById(String.valueOf(id));
    }

    public void deleteById(ObjectId id){
        journalEntryRepo.deleteById(String.valueOf(id));
    }

    public Journal updateById(ObjectId id, Journal journal){
        Journal journalUpdated = journalEntryRepo.findById(String.valueOf(id)).get();
        journalUpdated.setTitle(journal.getTitle());
        journalUpdated.setDescription(journal.getDescription());
        journalEntryRepo.save(journalUpdated);
        return journalUpdated;
    }
}
