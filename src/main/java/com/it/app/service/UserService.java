package com.it.app.service;

import com.it.app.model.User;

import java.util.List;

public interface UserService {

    User save(User user);

    void deleteById(Long id);

    void delete(User entity);

    User getOne(Long id);

    User update(User user);

    User findById(Long id);

    List<User> findAll();

    User findByUsername(String username);

    User findByEmail(String email);
}
