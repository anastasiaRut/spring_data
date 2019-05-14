package com.it.app.controller;

import com.it.app.component.LocalizedMessageSource;
import com.it.app.dto.RoleDto;
import com.it.app.model.Role;
import com.it.app.service.RoleService;
import org.dozer.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * The class represents a REST Controller for Role entity
 *
 * @author A. Rutkouskaya
 * @see Role
 */
@RestController
@RequestMapping("/roles")
public class RoleController {
    private final Mapper mapper;

    private final RoleService roleService;

    private final LocalizedMessageSource localizedMessageSource;

    public RoleController(Mapper mapper, RoleService roleService, LocalizedMessageSource localizedMessageSource) {
        this.mapper = mapper;
        this.roleService = roleService;
        this.localizedMessageSource = localizedMessageSource;
    }

    /**
     * Gets all Roles
     *
     * @return ResponseEntity<List < RoleResponseDto>>
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<RoleDto>> getAll() {
        final List<Role> roles = roleService.findAll();
        final List<RoleDto> roleDtoList = roles.stream()
                .map((Role) -> mapper.map(Role, RoleDto.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(roleDtoList, HttpStatus.OK);
    }

    /**
     * Gets one Role
     *
     * @param id -id
     * @return ResponseEntity<RoleResponseDto>
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<RoleDto> getOne(@PathVariable Long id) {
        final RoleDto roleDto = mapper.map(roleService.findById(id), RoleDto.class);
        return new ResponseEntity<>(roleDto, HttpStatus.OK);
    }

    /**
     * Saves Role
     *
     * @param roleDto - roleRDto
     * @return ResponseEntity<RoleResponseDto>
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<RoleDto> save(@Valid @RequestBody RoleDto roleDto) {
        roleDto.setId(null);
        final RoleDto responseRoleDto = mapper.map(roleService.save(mapper.map(roleDto, Role.class)), RoleDto.class);
        return new ResponseEntity<>(responseRoleDto, HttpStatus.OK);
    }

    /**
     * Updates Role
     *
     * @param roleDto - roleDto
     * @return ResponseEntity<RoleResponseDto>
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<RoleDto> update(@Valid @RequestBody RoleDto roleDto, @PathVariable Long id) {
        if (!Objects.equals(id, roleDto.getId())) {
            throw new RuntimeException(localizedMessageSource.getMessage("controller.role.unexpectedId", new Object[]{}));
        }
        final RoleDto responseRoleDto = mapper.map(roleService.update(mapper.map(roleDto, Role.class)), RoleDto.class);
        return new ResponseEntity<>(responseRoleDto, HttpStatus.OK);
    }

    /**
     * Deletes Role
     *
     * @param id - id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        roleService.deleteById(id);
    }
}
