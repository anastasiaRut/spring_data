package com.it.app.repository;

import com.it.app.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Interface for operations on a repository for Role entity
 *
 * @author A. Rutkouskaya
 * @see Role
 */
public interface RoleRepository extends JpaRepository<Role, Long> {

    /**
     * Find Role and fetching Users
     * JPA QL implementation
     *
     * @param nameOfRole - nameOfRole
     * @return Role
     */
    @Query("FROM Role role JOIN FETCH role.users WHERE role.name = :name")
    Role findRoleWithUsers(@Param("name") String nameOfRole);

    /**
     * Find page of All Roles and fetching Users
     * JPA QL implementation
     *
     * @return List<Role>
     */
    @Query("FROM Role role JOIN FETCH role.users")
    List<Role> findAllWithUsers();

}