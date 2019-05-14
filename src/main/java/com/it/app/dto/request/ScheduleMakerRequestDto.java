package com.it.app.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Request Data Transfer Object class for ScheduleMaker entity
 */
@Getter
@Setter
public class ScheduleMakerRequestDto {
    private Long id;

    @NotNull(message = "{scheduleMaker.name.notNull}")
    @NotEmpty(message = "{scheduleMaker.name.notEmpty}")
    @Size(min = 3, max = 50, message = "{scheduleMaker.name.size}")
    private String name;

    @NotNull(message = "{scheduleMaker.role.notNull}")
    private Long roleId;

    @NotNull(message = "{scheduleMaker.language.notNull}")
    private Long languageId;

    @NotNull(message = "{ user.username.notNull}")
    @NotEmpty(message = "{ user.username.notEmpty}")
    private String username;

    @NotNull(message = "{ user.password.notNull}")
    @NotEmpty(message = "{ user.password.notEmpty}")
    private String password;
}
