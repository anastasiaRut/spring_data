package com.it.app.service;

import com.it.app.model.User;

import java.util.List;

public interface UserService {

    void deleteById(Long id);

    void delete(User entity);

    User getOne(Long id);

    User editUser(User user);

    List<User> findAll();

    User findByUsername(String username);

    User findByEmail(String email);
}
