package com.it.app.controller;

import com.it.app.component.LocalizedMessageSource;
import com.it.app.dto.TypeOfCourseDto;
import com.it.app.model.TypeOfCourse;
import com.it.app.service.TypeOfCourseService;
import org.dozer.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/types")
public class TypeOfCourseController {
    private final Mapper mapper;

    private final TypeOfCourseService typeOfCourseService;

    private final LocalizedMessageSource localizedMessageSource;

    public TypeOfCourseController(Mapper mapper, TypeOfCourseService typeOfCourseService, LocalizedMessageSource localizedMessageSource) {
        this.mapper = mapper;
        this.typeOfCourseService = typeOfCourseService;
        this.localizedMessageSource = localizedMessageSource;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<TypeOfCourseDto>> getAll() {
        final List<TypeOfCourse> typeOfCourses = typeOfCourseService.findAll();
        final List<TypeOfCourseDto> typeOfCourseDtoList = typeOfCourses.stream()
                .map((TypeOfCourse) -> mapper.map(TypeOfCourse, TypeOfCourseDto.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(typeOfCourseDtoList, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<TypeOfCourseDto> getOne(@PathVariable Long id) {
        final TypeOfCourseDto typeOfCourseDto = mapper.map(typeOfCourseService.findById(id), TypeOfCourseDto.class);
        return new ResponseEntity<>(typeOfCourseDto, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<TypeOfCourseDto> save(@Valid @RequestBody TypeOfCourseDto typeOfCourseDto) {
        typeOfCourseDto.setId(null);
        final TypeOfCourseDto responseTypeOfCourseDto = mapper.map(typeOfCourseService.save(mapper.map(typeOfCourseDto, TypeOfCourse.class)), TypeOfCourseDto.class);
        return new ResponseEntity<>(responseTypeOfCourseDto, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<TypeOfCourseDto> update(@Valid @RequestBody TypeOfCourseDto typeOfCourseDto, @PathVariable Long id) {
        if (!Objects.equals(id, typeOfCourseDto.getId())) {
            throw new RuntimeException(localizedMessageSource.getMessage("controller.typeOfCourse.unexpectedId", new Object[]{}));
        }
        final TypeOfCourseDto responseTypeOfCourseDto = mapper.map(typeOfCourseService.update(mapper.map(typeOfCourseDto, TypeOfCourse.class)), TypeOfCourseDto.class);
        return new ResponseEntity<>(responseTypeOfCourseDto, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        typeOfCourseService.deleteById(id);
    }
}
