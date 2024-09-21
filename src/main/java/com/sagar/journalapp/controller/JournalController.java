package com.sagar.journalapp.controller;

import com.sagar.journalapp.entity.Journal;
import com.sagar.journalapp.service.JournalService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/journal/")
public class JournalController {

    @Autowired
    private JournalService journalService;

    @GetMapping
    public String hello(){
        return "Hello World";
    }

    @PostMapping("create")
    public Journal createPost(@RequestBody Journal journal){
        journal.setPublishedDate(LocalDateTime.now());
        journalService.saveEntry(journal);
        return journal;
    }

    @GetMapping("get-all")
    public List<Journal> getAll(){
        return journalService.getAllJournals();
    }

    @GetMapping("get/{id}")
    public Journal getJournalById(@PathVariable ObjectId id){
        return journalService.getJournalPostById(id).orElse(null);
    }

    @DeleteMapping("delete/{id}")
    public boolean deleteById(ObjectId id){
        journalService.deleteById(id);
        return true;
    }

    @PutMapping("update/{id}")
    public Journal updateById(@PathVariable ObjectId id, @RequestBody Journal journal){
        journalService.updateById(id, journal);
        return journal;
    }

}
