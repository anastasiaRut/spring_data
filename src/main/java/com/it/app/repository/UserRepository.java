package com.it.app.repository;

import com.it.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface for operations on a repository for User entity
 *
 * @author A. Rutkouskaya
 * @see User
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Finds User by username
     *
     * @param username
     * @return User
     */
    User findByUsername(String username);

    /**
     * Finds User by e-mail
     *
     * @param email - e-mail
     * @return User
     */
    User findByEmail(String email);
}
