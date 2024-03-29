package com.tweetero.api.models;

import java.net.URL;

import com.tweetero.api.dto.TweetsDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Tweets {
    public  Tweets(TweetsDTO req, Users user) {
        this.username = user.getUsername();
        this.avatar = user.getAvatar();
        this.text = req.text();

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String username;

    private URL avatar;

    private String text;
}
