package com.it.app.service;

import com.it.app.model.Role;

import java.util.List;

public interface RoleService {
    Role save(Role role);

    void deleteById(Long id);

    void delete(Role entity);

    Role getOne(Long id);

    Role update(Role role);

    Role findById(Long id);

    List<Role> findAll();

    Role findRoleWithUsers(String nameOfRole);

    List<Role> findAllWithUsers();
}
