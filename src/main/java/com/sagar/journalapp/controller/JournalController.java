package com.sagar.journalapp.controller;

import com.sagar.journalapp.entity.Journal;
import com.sagar.journalapp.service.JournalService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<Journal> createPost(@RequestBody Journal journal){
        try{
            journal.setPublishedDate(LocalDateTime.now());
            journalService.saveEntry(journal);

            if(journal.getId() != null){
                return new ResponseEntity<>(journal, HttpStatus.CREATED);
            }

            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("get-all")
    public ResponseEntity<?> getAll(){
        List<Journal> postList = journalService.getAllJournals();
        return new ResponseEntity<>(postList, HttpStatus.OK);

    }

    // ResponseEntity<?> -> means wildcard entity pass
    @GetMapping("get/{id}")
    public ResponseEntity<Journal> getJournalById(@PathVariable ObjectId id){
        Optional<Journal> journal = journalService.getJournalPostById(id);
        if(journal.isPresent()){
            return new ResponseEntity<>(journal.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteById(ObjectId id){
        journalService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<?> updateById(@PathVariable ObjectId id, @RequestBody Journal journal){
        journalService.updateById(id, journal);
        return new ResponseEntity<>(journal, HttpStatus.OK);
    }

}
