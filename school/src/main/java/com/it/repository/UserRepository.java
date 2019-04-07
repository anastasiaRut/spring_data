package com.it.repository;

import com.it.model.User;
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
     * @param e_mail - e-mail
     * @return User
     */
    User findByE_mail(String e_mail);
}
