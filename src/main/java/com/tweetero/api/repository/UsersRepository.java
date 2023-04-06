package com.tweetero.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tweetero.api.models.Users;

public interface UsersRepository extends JpaRepository<Users, Long> {
    
}
