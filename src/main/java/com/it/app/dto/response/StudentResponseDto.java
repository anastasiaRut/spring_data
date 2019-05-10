package com.it.app.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Response Data Transfer Object class for Student entity
 */
@Getter
@Setter
@NoArgsConstructor
public class StudentResponseDto {
    private Long id;
    private String name;
    private Long roleId;
    private String phoneNumber;
    private String username;
    private String password;

}
