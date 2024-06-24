package dev.gwozdz.contactfinder.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpResponse;

@RestController("contacts")
public class ContactsResource {


    @PostMapping(params = {"phrase"})
    public ResponseEntity<String> startCreating(@RequestParam(name = "phrase") String phrase){
        return new ResponseEntity<>("started", HttpStatus.CREATED);
    }
}
