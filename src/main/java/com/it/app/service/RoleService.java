package com.it.app.service;

import com.it.app.model.Role;

import java.util.List;

public interface RoleService {
    Role addRole(Role role);

    void deleteById(Long id);

    void delete(Role entity);

    Role getOne(Long id);

    Role editRole(Role role);

    List<Role> findAll();

    Role findRoleWithUsers(String nameOfRole);

    List<Role> findAllWithUsers();
}
