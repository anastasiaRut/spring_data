package com.it.app.service.impl;

import com.it.app.model.Role;
import com.it.app.repository.RoleRepository;
import com.it.app.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleRepository roleRepository;

    @Override
    public Role addRole(Role role) {
        Role savedRole = roleRepository.saveAndFlush(role);
        return savedRole;
    }

    @Override
    public void deleteById(Long id) {
        roleRepository.deleteById(id);
    }

    @Override
    public void delete(Role entity) {
        roleRepository.delete(entity);
    }

    @Override
    public Role getOne(Long id) {
        return roleRepository.getOne(id);
    }

    @Override
    public Role editRole(Role role) {
        return roleRepository.saveAndFlush(role);
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role findRoleWithUsers(String nameOfRole) {
        return roleRepository.findRoleWithUsers(nameOfRole);
    }

    @Override
    public List<Role> findAllWithUsers() {
        return roleRepository.findAllWithUsers();
    }
}
