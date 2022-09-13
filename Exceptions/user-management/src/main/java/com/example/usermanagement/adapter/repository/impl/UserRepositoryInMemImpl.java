package com.example.usermanagement.adapter.repository.impl;

import com.example.usermanagement.adapter.repository.UserRepository;
import com.example.usermanagement.model.User;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class UserRepositoryInMemImpl implements UserRepository {

    Map<String,User> users = new ConcurrentHashMap<String,User>() {

        {  put("20",User.builder().id ("20").userName("OmarMohammad").birthdate(LocalDate.of(1997,9,13)).build());
            put("21",User.builder().id("21").userName("AhmdAli").birthdate(LocalDate.of(1959,2,24)).build());
            put("22",User.builder().id("22").userName("Mo7snMahmod").birthdate(LocalDate.of(1985,6,19)).build());
            put("23",User.builder().id("23").userName("AbdoHamad").birthdate(LocalDate.of(2001,4,22)).build());}
    };

    @Override
    public Optional<User> getById(String id) {
        return Optional.ofNullable(users.get(id));
    }

    @Override
    public User save(User user) {
        users.put(user.getId(), user);
        return users.get(user.getId());

    }

    @Override
    public Optional<User> update(User user) {
        users.put(user.getId(), user);
        return Optional.ofNullable(users.get(user.getId()));

    }

    @Override
    public User remove(String id) {
        return users.remove(id);
    }

    @Override
    public boolean hasUser(String id) {
        return users.containsKey(id);
    }
}
