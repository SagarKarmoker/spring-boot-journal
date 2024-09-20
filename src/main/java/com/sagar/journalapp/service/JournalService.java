package com.sagar.journalapp.service;

import com.sagar.journalapp.entity.Journal;
import com.sagar.journalapp.repo.JournalEntryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JournalService {
    // business logic here
    @Autowired
    private JournalEntryRepo journalEntryRepo;

    public void saveEntry(Journal journal){
        journalEntryRepo.save(journal);
    }

    public List<Journal> getAllJournals(){
        journalEntryRepo.findAll();
        System.out.println(journalEntryRepo.findAll());
        return journalEntryRepo.findAll();
    }
}
