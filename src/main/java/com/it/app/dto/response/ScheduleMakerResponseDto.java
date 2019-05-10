package com.it.app.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Response Data Transfer Object class for ScheduleMaker entity
 */
@Getter
@Setter
@NoArgsConstructor
public class ScheduleMakerResponseDto {
    private Long id;
    private String name;
    private Long roleId;
    private Long languageId;
    private String username;
    private String password;

}
