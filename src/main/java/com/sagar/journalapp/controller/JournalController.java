package com.sagar.journalapp.controller;

import com.sagar.journalapp.entity.Journal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/journal")
public class JournalController {

    @GetMapping("/")
    public String hello(){
        return "Hello World";
    }

    @PostMapping("/create")
    public Journal createPost(@RequestBody Journal journal){
        return journal;
    }

}
