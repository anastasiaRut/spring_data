package com.it.app.controller;

import com.it.app.component.LocalizedMessageSource;
import com.it.app.dto.request.CourseRequestDto;
import com.it.app.dto.response.CourseResponseDto;
import com.it.app.model.*;
import com.it.app.service.CourseService;
import org.dozer.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    private final Mapper mapper;

    private final LocalizedMessageSource localizedMessageSource;

    public CourseController(CourseService courseService, Mapper mapper, LocalizedMessageSource localizedMessageSource) {
        this.courseService = courseService;
        this.mapper = mapper;
        this.localizedMessageSource = localizedMessageSource;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<CourseResponseDto>> getAll() {
        final List<Course> courses = courseService.findAll();
        final List<CourseResponseDto> courseDtoList = new ArrayList<>();
        courses.stream()
                .forEach((Course) -> courseDtoList.add(getCourseDto(Course)));
        return new ResponseEntity<>(courseDtoList, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<CourseResponseDto> save(@Valid @RequestBody CourseRequestDto courseRequestDto) {
        courseRequestDto.setId(null);
        final CourseResponseDto courseResponseDto = getCourseDto(courseService.save(getCourse(courseRequestDto)));
        return new ResponseEntity<>(courseResponseDto, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<CourseResponseDto> update(@Valid @RequestBody CourseRequestDto courseRequestDto, @PathVariable Long id) {
        if (!Objects.equals(id, courseRequestDto.getId())) {
            throw new RuntimeException(localizedMessageSource.getMessage("controller.course.unexpectedId", new Object[]{}));
        }
        final CourseResponseDto courseResponseDto = getCourseDto(courseService.update(getCourse(courseRequestDto)));
        return new ResponseEntity<>(courseResponseDto, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        courseService.deleteById(id);
    }

    private Course getCourse(CourseRequestDto courseRequestDto) {
        final Course course = mapper.map(courseRequestDto, Course.class);
        final Language language = new Language();
        final Level level = new Level();
        final TypeOfCourse typeOfCourse = new TypeOfCourse();
        final Tutor tutor = new Tutor();
        language.setId(courseRequestDto.getLanguageId());
        course.setLanguage(language);

        level.setId(courseRequestDto.getLevelId());
        course.setLevel(level);

        typeOfCourse.setId(courseRequestDto.getTypeOfCourseId());
        course.setTypeOfCourse(typeOfCourse);

        tutor.setId(courseRequestDto.getTutorId());
        course.setTutor(tutor);

        return course;
    }

    private CourseResponseDto getCourseDto(Course course) {
        final CourseResponseDto courseResponseDto = mapper.map(course, CourseResponseDto.class);
        courseResponseDto.setLanguageId(course.getLanguage().getId());
        courseResponseDto.setLevelId(course.getLevel().getId());
        courseResponseDto.setTutorId(course.getTutor().getId());
        courseResponseDto.setTypeOfCourseId(course.getTypeOfCourse().getId());

        return courseResponseDto;
    }
}
