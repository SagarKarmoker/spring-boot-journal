package com.sagar.journalapp.controller;

import com.sagar.journalapp.entity.Journal;
import com.sagar.journalapp.entity.User;
import com.sagar.journalapp.service.JournalService;
import com.sagar.journalapp.service.UserService;
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
    @Autowired
    private UserService userService;

    @GetMapping
    public String hello(){
        return "Hello World";
    }

    @PostMapping("create/{username}")
    public ResponseEntity<Journal> createPost(@RequestBody Journal journal, @PathVariable String username){
        try{
            journal.setPublishedDate(LocalDateTime.now());
            journalService.saveEntry(journal, username);

            if(journal.getId() != null){
                return new ResponseEntity<>(journal, HttpStatus.CREATED);
            }

            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("get-all/{username}")
    public ResponseEntity<?> getAllJournalByUsername(@PathVariable String username){
        User user = userService.findByUsername(username);
        List<Journal> postList = user.getJournals();
        if(postList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
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

    @DeleteMapping("delete/{username}/{id}")
    public ResponseEntity<?> deleteById(@PathVariable ObjectId id, @PathVariable String username){
        journalService.deleteById(id, username);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<?> updateById(
            @PathVariable ObjectId id,
            @RequestBody Journal journal){
        journalService.updateById(id, journal);
        return new ResponseEntity<>(journal, HttpStatus.OK);
    }

}
