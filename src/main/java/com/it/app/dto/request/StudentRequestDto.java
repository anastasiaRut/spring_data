package com.it.app.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Request Data Transfer Object class for Student entity
 */
@Getter
@Setter
public class StudentRequestDto {
    private Long id;

    @NotNull(message = "{student.name.notNull}")
    @NotEmpty(message = "{student.name.notEmpty}")
    @Size(min = 3, max = 50, message = "{student.name.size}")
    private String name;

    @NotNull(message = "{ user.username.notNull}")
    @NotEmpty(message = "{ user.username.notEmpty}")
    private String username;

    @NotNull(message = "{ user.password.notNull}")
    @NotEmpty(message = "{ user.password.notEmpty}")
    private String password;

    @NotNull(message = "{student.role.notNull}")
    private Long roleId;

    @Pattern(regexp = "^[+]375(29|33|25|44)[0-9]{7}$", message = "{student.phoneNumber.valid}")
    private String phoneNumber;


}
