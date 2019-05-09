package com.it.app.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDate;

@Getter
@Setter
public class CourseRequestDto {
    private Long id;

    private String startDate;

    @NotNull(message = "{course.level.notNull}")
    private Long levelId;

    @NotNull(message = "{course.language.notNull}")
    private Long languageId;

    @NotNull(message = "{course.tutor.notNull}")
    private Long tutorId;

    @NotNull(message = "{course.typeOfCourse.notNull}")
    private Long typeOfCourseId;

    @PositiveOrZero(message = "{course.places.positiveOrZero}")
    private Short places;
}
