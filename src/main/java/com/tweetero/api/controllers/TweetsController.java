package com.tweetero.api.controllers;

import java.util.ArrayList;
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
    public ResponseEntity<String> create(@RequestBody TweetsDTO req) {
        Users user = userRepository.getByUsername(req.username());
        if (user != null) {
            repository.save(new Tweets(req, user.getAvatar()));
            return ResponseEntity.status(HttpStatus.OK).body("Tweet created successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User does not exist");
        }
    }

    @GetMapping("/tweets")
    public List<Tweets> getAllTweets(@RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "5") Integer size) {
        Pageable paging = PageRequest.of(page, size, Sort.by("id").descending());

        Page<Tweets> pagedResult = repository.findAll(paging);

        if (pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Tweets>();
        }
    }

    @GetMapping("/tweets/{username}")
    public List<Tweets> getByName(@PathVariable String username) {
        return repository.getByUsername(username);
    }
}
