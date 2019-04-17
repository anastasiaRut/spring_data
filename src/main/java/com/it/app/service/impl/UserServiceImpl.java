package com.it.app.service.impl;

import com.it.app.model.User;
import com.it.app.repository.UserRepository;
import com.it.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void delete(User entity) {
        userRepository.delete(entity);
    }

    @Override
    public User getOne(Long id) {
        return userRepository.getOne(id);
    }

    @Override
    public User editUser(User user) {
        return userRepository.saveAndFlush(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
