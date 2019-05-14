package com.it.app.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Response Data Transfer Object class for User entity
 */
@Getter
@Setter
@NoArgsConstructor
public class UserResponseDto {
    private Long id;

    private String name;

    private String password;

    private Long roleId;
}
