package com.tweetero.api.models;

import java.net.URL;

import com.tweetero.api.dto.UsersDTO;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Users {
    private void Users(UsersDTO req) {
        this.username = req.username();
        this.avatar = req.avatar();

    }

    private long id;

    private String username;

    private URL avatar;
}
