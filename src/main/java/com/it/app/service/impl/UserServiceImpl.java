package com.it.app.service.impl;

import com.it.app.component.LocalizedMessageSource;
import com.it.app.model.User;
import com.it.app.repository.UserRepository;
import com.it.app.service.RoleService;
import com.it.app.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * The class represents a Implementation of UserService interface
 *
 * @author A. Rutkouskaya
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final LocalizedMessageSource localizedMessageSource;

    private final RoleService roleService;

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository, RoleService roleService, LocalizedMessageSource localizedMessageSource) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.localizedMessageSource = localizedMessageSource;
    }

    /**
     * @see UserService#findAll()
     */
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    /**
     * @see UserService#findById(Long)
     */
    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException(localizedMessageSource.getMessage("error.user.notExist", new Object[]{})));
    }

    /**
     * @see UserService#save(User)
     */
    @Override
    public User save(User user) {
        validate(user.getId() != null, localizedMessageSource.getMessage("error.user.notHaveId", new Object[]{}));
        validate(userRepository.existsByUsername(user.getUsername()), localizedMessageSource.getMessage("error.user.username.notUnique", new Object[]{}));
        return saveAndFlush(user);
    }

    /**
     * @see UserService#update(User)
     */
    @Override
    public User update(User user) {
        final Long id = user.getId();
        validate(id == null, localizedMessageSource.getMessage("error.user.haveId", new Object[]{}));
        final User duplicateUser = userRepository.findByUsername(user.getUsername());
        final boolean isDuplicateExists = duplicateUser != null && !Objects.equals(duplicateUser.getId(), id);
        validate(isDuplicateExists, localizedMessageSource.getMessage("error.user.username.notUnique", new Object[]{}));
        findById(id);
        return saveAndFlush(user);
    }

    /**
     * @see UserService#delete(User)
     */
    @Override
    public void delete(User user) {
        final Long id = user.getId();
        validate(id == null, localizedMessageSource.getMessage("error.user.haveId", new Object[]{}));
        findById(id);
        userRepository.delete(user);
    }

    /**
     * @see UserService#getOne(Long)
     */
    @Override
    public User getOne(Long id) {
        return userRepository.getOne(id);
    }

    /**
     * @see UserService#deleteById(Long)
     */
    @Override
    public void deleteById(Long id) {
        findById(id);
        userRepository.deleteById(id);
    }

    /**
     * @see UserService#findByUsername(String)
     */
    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    /**
     * @see UserService#findByEmail(String)
     */
    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    private User saveAndFlush(User user) {
        validate(user.getRole() == null || user.getRole().getId() == null, localizedMessageSource.getMessage("error.user.role.isNull", new Object[]{}));
        user.setRole(roleService.findById(user.getRole().getId()));
        return userRepository.saveAndFlush(user);
    }

    private void validate(boolean expression, String errorMessage) {
        if (expression) {
            throw new RuntimeException(errorMessage);
        }
    }

}
