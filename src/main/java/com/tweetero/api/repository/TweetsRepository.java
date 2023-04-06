package com.tweetero.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tweetero.api.models.Tweets;

public interface TweetsRepository extends JpaRepository<Tweets, Long> {
    
}
