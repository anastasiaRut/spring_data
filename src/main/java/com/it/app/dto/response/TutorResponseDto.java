package com.it.app.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

/**
 * Response Data Transfer Object class for Tutor entity
 */
@Getter
@Setter
@NoArgsConstructor
public class TutorResponseDto {
    private Long id;
    private String name;
    private Long languageId;
    private Set<Long> levelsId;
    private String surname;

}
