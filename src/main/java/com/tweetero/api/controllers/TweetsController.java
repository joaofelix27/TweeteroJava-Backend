package com.tweetero.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tweetero.api.dto.TweetsDTO;
import com.tweetero.api.models.Tweets;
import com.tweetero.api.models.Users;
import com.tweetero.api.repository.TweetsRepository;
import com.tweetero.api.repository.UsersRepository;

@RestController
@RequestMapping("/api")
public class TweetsController {

    @Autowired
    private TweetsRepository repository;

    @Autowired
    private UsersRepository userRepository;

    @PostMapping("/tweets")
    public String create(@RequestBody TweetsDTO req) {
        Users user = userRepository.getByUsername(req.username());
        repository.save(new Tweets(req,user.getAvatar()));
        return "OK";
    }
}
