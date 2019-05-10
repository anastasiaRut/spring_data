package com.it.app.controller;

import com.it.app.component.LocalizedMessageSource;
import com.it.app.dto.request.StudentRequestDto;
import com.it.app.dto.response.StudentResponseDto;
import com.it.app.model.Role;
import com.it.app.model.Student;
import com.it.app.service.StudentService;
import org.dozer.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The class represents a REST Controller for Student entity
 *
 * @author A. Rutkouskaya
 * @see Student
 */
@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    private final Mapper mapper;

    private final LocalizedMessageSource localizedMessageSource;

    public StudentController(StudentService studentService, Mapper mapper, LocalizedMessageSource localizedMessageSource) {
        this.studentService = studentService;
        this.mapper = mapper;
        this.localizedMessageSource = localizedMessageSource;
    }

    /**
     * Gets all Students
     *
     * @return ResponseEntity<List<StudentResponseDto>>
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<StudentResponseDto>> getAll() {
        final List<Student> students = studentService.findAll();
        final List<StudentResponseDto> studentDtoList = new ArrayList<>();
        students.stream()
                .forEach((Student) -> studentDtoList.add(getStudentDto(Student)));

        return new ResponseEntity<>(studentDtoList, HttpStatus.OK);
    }

    /**
     * Gets one Student
     *
     * @param id -id
     * @return ResponseEntity<StudentResponseDto>
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<StudentResponseDto> getOne(@PathVariable Long id) {
        final StudentResponseDto studentResponseDto = getStudentDto(studentService.findById(id));
        return new ResponseEntity<>(studentResponseDto, HttpStatus.OK);
    }

    /**
     * Saves Student
     *
     * @param studentRequestDto - studentRequestDto
     * @return ResponseEntity<StudentResponseDto>
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<StudentResponseDto> save(@Valid @RequestBody StudentRequestDto studentRequestDto) {
        studentRequestDto.setId(null);
        final StudentResponseDto studentResponseDto = getStudentDto(studentService.save(getStudent(studentRequestDto)));
        return new ResponseEntity<>(studentResponseDto, HttpStatus.OK);
    }

    /**
     * Updates Student
     *
     * @param studentRequestDto - studentRequestDto
     * @return ResponseEntity<StudentResponseDto>
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<StudentResponseDto> update(@Valid @RequestBody StudentRequestDto studentRequestDto, @PathVariable Long id) {
        if (!Objects.equals(id, studentRequestDto.getId())) {
            throw new RuntimeException(localizedMessageSource.getMessage("controller.student.unexpectedId", new Object[]{}));
        }
        final StudentResponseDto studentResponseDto = getStudentDto(studentService.update(getStudent(studentRequestDto)));
        return new ResponseEntity<>(studentResponseDto, HttpStatus.OK);
    }

    /**
     * Deletes Student
     *
     * @param id - id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        studentService.deleteById(id);
    }

    private Student getStudent(StudentRequestDto studentRequestDto) {
        final Student student = mapper.map(studentRequestDto, Student.class);
        final Role role = new Role();
        role.setId(studentRequestDto.getRoleId());
        student.setRole(role);
        return student;
    }

    private StudentResponseDto getStudentDto(Student student) {
        final StudentResponseDto studentResponseDto = mapper.map(student, StudentResponseDto.class);
        studentResponseDto.setRoleId(student.getRole().getId());
        return studentResponseDto;
    }


}
