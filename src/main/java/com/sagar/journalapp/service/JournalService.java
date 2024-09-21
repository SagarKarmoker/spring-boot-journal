package com.sagar.journalapp.service;

import com.sagar.journalapp.entity.Journal;
import com.sagar.journalapp.entity.User;
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

    @Autowired
    private UserService userService;

    public void saveEntry(Journal journal, String username){
        User user = userService.findByUsername(username);
        Journal saved = journalEntryRepo.save(journal);
        user.getJournals().add(saved);
        userService.saveUser(user); // createUser is just a method name
    }

    public void saveEntry(Journal journal){
        journalEntryRepo.save(journal);
    }

    public List<Journal> getAllJournals(){
        return journalEntryRepo.findAll();
    }

    public Optional<Journal> getJournalPostById(ObjectId id){
        return journalEntryRepo.findById(String.valueOf(id));
    }

    public void deleteById(ObjectId id, String username){
        User user = userService.findByUsername(username);
        user.getJournals().removeIf(post -> post.getId().equals(id));
        userService.saveUser(user);
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
