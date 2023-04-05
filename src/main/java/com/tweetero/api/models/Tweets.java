package com.tweetero.api.models;

import java.net.URL;

import com.tweetero.api.dto.TweetsDTO;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Tweets {
    private void Tweets(TweetsDTO req) {
        this.username = req.username();
        this.avatar = req.avatar();
        this.text = req.text();

    }

    private long id;

    private String username;

    private URL avatar;

    private String text;
}
