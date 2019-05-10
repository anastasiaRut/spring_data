package com.it.app.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Response Data Transfer Object class for StudentCourse entity
 */
@Getter
@Setter
@NoArgsConstructor
public class StudentCourseResponseDto {
    private Long id;
    private Long studentid;
    private Long courseid;
    private Boolean status;
}
