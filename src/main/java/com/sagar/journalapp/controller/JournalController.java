package com.sagar.journalapp.controller;

import com.sagar.journalapp.entity.Journal;
import com.sagar.journalapp.service.JournalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/journal")
public class JournalController {

    @Autowired
    private JournalService journalService;

    @GetMapping("/")
    public String hello(){
        return "Hello World";
    }

    @PostMapping("/create")
    public Journal createPost(@RequestBody Journal journal){
        journalService.saveEntry(journal);
        return journal;
    }

    @GetMapping("/getall")
    public List<Journal> getAll(){
        return journalService.getAllJournals();
    }

}
