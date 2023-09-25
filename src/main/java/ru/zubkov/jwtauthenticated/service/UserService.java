package ru.zubkov.jwtauthenticated.service;

import ru.zubkov.jwtauthenticated.model.User;

import java.util.List;

public interface UserService {

    User register(User user);

    List<User> getAll();

    User findByUserName(String userName);

    User findById(Long id);

    void delete(Long id);

    User save(User user);
}
