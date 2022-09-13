package com.example.usermanagement.adapter.repository;

import com.example.usermanagement.model.User;

import java.util.Collection;
import java.util.Optional;

public interface UserRepository {
    Optional<User> getById(String id);

    User save(User user);

    Optional<User> update(User user);

    User remove(String id);

    boolean hasUser(String id);

    Collection<User> findAll();
}
