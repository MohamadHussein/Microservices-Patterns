package com.example.usermanagement.adapter.repository;

import com.example.usermanagement.model.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> getById(String id);
}
