package com.it.app.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class TutorRequestDto {
    private Long id;

    @NotNull(message = "{tutor.name.notNull}")
    @NotEmpty(message = "{tutor.name.notEmpty}")
    @Size(min = 3, max = 50, message = "{tutor.name.size}")
    private String name;

    @NotNull(message = "{ tutor.surname.notNull}")
    @NotEmpty(message = "{ tutor.surname.notEmpty}")
    @Size(min = 3, max = 50, message = "{ tutor.surname.size}")
    private String surname;

    @NotNull(message = "{tutor.levels.notNull}")
    private Set<Long> levelsId;

    @NotNull(message = "{tutor.language.notNull}")
    private Long languageId;
}
