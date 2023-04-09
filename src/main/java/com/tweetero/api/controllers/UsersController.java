package com.tweetero.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tweetero.api.dto.UsersDTO;
import com.tweetero.api.models.Users;
import com.tweetero.api.repository.UsersRepository;

@RestController
@RequestMapping("/api")
public class UsersController {

    @Autowired
    private UsersRepository repository;

    @PostMapping("/auth/sign-up")
    public ResponseEntity<String> create(@RequestBody UsersDTO req) {
        Users user = repository.getByUsername(req.username());
        if (user != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User with email already exists");
        } else {
            repository.save(new Users(req));
            return ResponseEntity.status(HttpStatus.OK).body("User created successfully");
        }
    }
}
