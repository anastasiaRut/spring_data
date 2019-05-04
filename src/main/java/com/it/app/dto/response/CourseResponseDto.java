package com.it.app.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class CourseResponseDto {
    private Long id;
    private LocalDate startDate;
    private Long levelId;
    private Long languageId;
    private Long tutorId;
    private Long typeOfCourseId;
    private Short places;

}
