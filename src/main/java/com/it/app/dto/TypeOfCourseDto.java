package com.it.app.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Getter
@Setter
public class TypeOfCourseDto {
    private Long id;

    @NotNull(message = "{typeOfCourse.name.notNull}")
    @NotEmpty(message = "{typeOfCourse.name.notEmpty}")
    @Size(min = 3, max = 50, message = "{typeOfCourse.name.size}")
    private String name;

    @NotNull(message = "{typeOfCourse.timeType.notNull}")
    @NotEmpty(message = "{typeOfCourse.timeType.notEmpty}")
    private String timeType;

    @NotNull(message = "{typeOfCourse.cost.notNull}")
    @Positive(message = "{typeOfCourse.cost.positive}")
    private BigDecimal cost;
}
