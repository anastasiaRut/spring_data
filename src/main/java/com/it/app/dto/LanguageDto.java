package com.it.app.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Data Transfer Object class for Language entity
 */
@Getter
@Setter
public class LanguageDto {
    private Long id;

    @NotNull(message = "{language.name.notNull}")
    @NotEmpty(message = "{language.name.notEmpty}")
    @Size(min = 3, max = 50, message = "{language.name.size}")
    private String name;
}
