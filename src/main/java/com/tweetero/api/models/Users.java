package com.tweetero.api.models;

import java.net.URL;

import com.tweetero.api.dto.UsersDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Users {

    public Users(UsersDTO req) {
        this.username = req.username();
        this.avatar = req.avatar();

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String username;

    private URL avatar;
}
