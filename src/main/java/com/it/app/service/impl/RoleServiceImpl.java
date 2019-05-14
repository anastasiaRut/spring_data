package com.it.app.service.impl;

import com.it.app.component.LocalizedMessageSource;
import com.it.app.model.Role;
import com.it.app.model.Role;
import com.it.app.repository.RoleRepository;
import com.it.app.service.RoleService;
import com.it.app.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * The class represents a Implementation of RoleService interface
 *
 * @author A. Rutkouskaya
 */
@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    private final LocalizedMessageSource localizedMessageSource;

    private final RoleRepository roleRepository;

    public RoleServiceImpl(LocalizedMessageSource localizedMessageSource, RoleRepository roleRepository) {
        this.localizedMessageSource = localizedMessageSource;
        this.roleRepository = roleRepository;
    }

    /**
     * @see RoleService#save(Role)
     */
    @Override
    public Role save(Role role) {
        validate(role.getId() != null, localizedMessageSource.getMessage("error.role.notHaveId", new Object[]{}));
        validate(roleRepository.existsByName(role.getName()), localizedMessageSource.getMessage("error.role.name.notUnique", new Object[]{}));
        return roleRepository.saveAndFlush(role);
    }

    /**
     * @see RoleService#deleteById(Long)
     */
    @Override
    public void deleteById(Long id) {
        findById(id);
        roleRepository.deleteById(id);
    }

    /**
     * @see RoleService#delete(Role)
     */
    @Override
    public void delete(Role entity) {
        final Long id = entity.getId();
        validate(id == null, localizedMessageSource.getMessage("error.role.haveId", new Object[]{}));
        findById(id);
        roleRepository.delete(entity);
    }

    /**
     * @see RoleService#getOne(Long)
     */
    @Override
    public Role getOne(Long id) {
        return roleRepository.getOne(id);
    }

    /**
     * @see RoleService#update(Role)
     */
    @Override
    public Role update(Role role) {
        validate(role.getId() == null, localizedMessageSource.getMessage("error.role.haveId", new Object[]{}));
        findById(role.getId());
        validate(roleRepository.existsByName(role.getName()), localizedMessageSource.getMessage("error.role.name.notUnique", new Object[]{}));
        return roleRepository.saveAndFlush(role);
    }

    /**
     * @see RoleService#findById(Long)
     */
    @Override
    public Role findById(Long id) {
        return roleRepository.findById(id).orElseThrow(() -> new RuntimeException(localizedMessageSource.getMessage("error.role.notExist", new Object[]{})));
    }

    /**
     * @see RoleService#findByName(String)
     */
    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }

    /**
     * @see RoleService#findAll()
     */
    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    /**
     * @see RoleService#findRoleWithUsers(String)
     */
    @Override
    public Role findRoleWithUsers(String nameOfRole) {
        return roleRepository.findRoleWithUsers(nameOfRole);
    }

    /**
     * @see RoleService#findAllWithUsers()
     */
    @Override
    public List<Role> findAllWithUsers() {
        return roleRepository.findAllWithUsers();
    }

    private void validate(boolean expression, String errorMessage) {
        if (expression) {
            throw new RuntimeException(errorMessage);
        }
    }
}
