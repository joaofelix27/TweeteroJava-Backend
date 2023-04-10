package com.tweetero.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public ResponseEntity<Tweets> create(@RequestHeader("User") String username, @RequestBody TweetsDTO req) {
        Users user = userRepository.getByUsername(username);
        if (user != null) {
            Tweets tweet = repository.save(new Tweets(req, user));
            return ResponseEntity.status(HttpStatus.CREATED).body(tweet);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("/tweets")
    public Page<Tweets> getAllTweets(@RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "5") Integer size) {
        Pageable paging = PageRequest.of(page, size, Sort.by("id").descending());

        return repository.findAll(paging);
    }

    @GetMapping("/tweets/{username}")
    public List<Tweets> getByName(@PathVariable String username) {
        return repository.getByUsername(username);
    }
}
