package com.it.app.controller;

import com.it.app.component.LocalizedMessageSource;
import com.it.app.dto.request.UserRequestDto;
import com.it.app.dto.response.UserResponseDto;
import com.it.app.model.Role;
import com.it.app.model.User;
import com.it.app.service.UserService;
import org.dozer.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/users")
public class UserController {
    private final Mapper mapper;

    private final UserService userService;

    private final LocalizedMessageSource localizedMessageSource;


    public UserController(Mapper mapper, UserService userService, LocalizedMessageSource localizedMessageSource) {
        this.mapper = mapper;
        this.userService = userService;
        this.localizedMessageSource = localizedMessageSource;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<UserResponseDto>> getAll() {
        final List<User> users = userService.findAll();
        final List<UserResponseDto> userResponseDtoList = new ArrayList<>();
        users.stream()
                .forEach((User) -> userResponseDtoList.add(getUserDto(User)));
        return new ResponseEntity<>(userResponseDtoList, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<UserResponseDto> getOne(@PathVariable Long id) {
        final UserResponseDto userResponseDto = getUserDto(userService.findById(id));
        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<UserResponseDto> save(@Valid @RequestBody UserRequestDto userRequestDto) {
        userRequestDto.setId(null);
        final UserResponseDto userResponseDto = getUserDto(userService.save(getUser(userRequestDto)));
        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<UserResponseDto> update(@Valid @RequestBody UserRequestDto userRequestDto, @PathVariable Long id) {
        if (!Objects.equals(id, userRequestDto.getId())) {
            throw new RuntimeException(localizedMessageSource.getMessage("controller.user.unexpectedId", new Object[]{}));
        }
        final UserResponseDto userResponseDto = getUserDto(userService.update(getUser(userRequestDto)));
        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        userService.deleteById(id);
    }

    private User getUser(UserRequestDto userRequestDto) {
        final User user = mapper.map(userRequestDto, User.class);
        final Role role = new Role();
        role.setId(userRequestDto.getRoleId());
        user.setRole(role);
        return user;
    }

    private UserResponseDto getUserDto(User user) {
        final UserResponseDto userResponseDto = mapper.map(user, UserResponseDto.class);
        userResponseDto.setRoleId(user.getRole().getId());
        return userResponseDto;
    }

}
