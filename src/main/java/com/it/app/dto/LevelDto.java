package com.it.app.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Data Transfer Object class for Level entity
 */
@Getter
@Setter
public class LevelDto {
    private Long id;

    @NotNull(message = "{level.name.notNull}")
    @NotEmpty(message = "{level.name.notEmpty}")
    @Size(min = 3, max = 50, message = "{level.name.size}")
    private String name;
}
