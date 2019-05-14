package com.it.app.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * Request Data Transfer Object class for StudentCourse entity
 */
@Getter
@Setter
public class StudentCourseRequestDto {
    private Long id;
    @NotNull(message = "{studentCourse.student.notNull}")
    private Long studentid;

    @NotNull(message = "{studentCourse.course.notNull}")
    private Long courseid;

    @NotNull(message = "{studentCourse.status.notNull}")

    private Boolean status;

}
