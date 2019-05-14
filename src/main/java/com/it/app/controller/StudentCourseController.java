package com.it.app.controller;

import com.it.app.component.LocalizedMessageSource;
import com.it.app.dto.request.StudentCourseRequestDto;
import com.it.app.dto.response.StudentCourseResponseDto;
import com.it.app.model.Course;
import com.it.app.model.Student;
import com.it.app.model.StudentCourse;
import com.it.app.service.StudentCourseService;
import org.dozer.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The class represents a REST Controller for StudentCourse entity
 *
 * @author A. Rutkouskaya
 * @see StudentCourse
 */
@RestController
@RequestMapping("/studentCourses")
public class StudentCourseController {

    private final StudentCourseService studentCourseService;

    private final Mapper mapper;

    private final LocalizedMessageSource localizedMessageSource;

    public StudentCourseController(StudentCourseService studentCourseService, Mapper mapper, LocalizedMessageSource localizedMessageSource) {
        this.studentCourseService = studentCourseService;
        this.mapper = mapper;
        this.localizedMessageSource = localizedMessageSource;
    }

    /**
     * Gets all StudentCourses
     *
     * @return ResponseEntity<List   <   StudentCourseResponseDto>>
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<StudentCourseResponseDto>> getAll() {
        final List<StudentCourse> studentCourses = studentCourseService.findAll();
        final List<StudentCourseResponseDto> studentCourseDtoList = new ArrayList<>();
        studentCourses.stream()
                .forEach((StudentCourse) -> studentCourseDtoList.add(getStudentCourseDto(StudentCourse)));
        return new ResponseEntity<>(studentCourseDtoList, HttpStatus.OK);
    }

    /**
     * Gets one StudentCourse
     *
     * @param id -id
     * @return ResponseEntity<StudentCourseResponseDto>
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<StudentCourseResponseDto> getOne(@PathVariable Long id) {
        final StudentCourseResponseDto studentCourseResponseDto = getStudentCourseDto(studentCourseService.findById(id));
        return new ResponseEntity<>(studentCourseResponseDto, HttpStatus.OK);
    }

    /**
     * Saves StudentCourse
     *
     * @param studentCourseRequestDto - studentCourseRequestDto
     * @return ResponseEntity<StudentCourseResponseDto>
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<StudentCourseResponseDto> save(@Valid @RequestBody StudentCourseRequestDto studentCourseRequestDto) {
        studentCourseRequestDto.setId(null);
        final StudentCourseResponseDto studentCourseResponseDto = getStudentCourseDto(studentCourseService.save(getStudentCourse(studentCourseRequestDto)));
        return new ResponseEntity<>(studentCourseResponseDto, HttpStatus.OK);
    }

    /**
     * Updates StudentCourse
     *
     * @param studentCourseRequestDto - studentCourseRequestDto
     * @return ResponseEntity<StudentCourseResponseDto>
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<StudentCourseResponseDto> update(@Valid @RequestBody StudentCourseRequestDto studentCourseRequestDto, @PathVariable Long id) {
        if (!Objects.equals(id, studentCourseRequestDto.getId())) {
            throw new RuntimeException(localizedMessageSource.getMessage("controller.studentCourse.unexpectedId", new Object[]{}));
        }
        final StudentCourseResponseDto studentCourseResponseDto = getStudentCourseDto(studentCourseService.update(getStudentCourse(studentCourseRequestDto)));
        return new ResponseEntity<>(studentCourseResponseDto, HttpStatus.OK);
    }

    /**
     * Deletes StudentCourse
     *
     * @param id - id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        studentCourseService.deleteById(id);
    }

    /**
     * Enroll student in course
     *
     * @param studentCourseRequestDto - studentCourseRequestDto
     * @return ResponseEntity<StudentCourseResponseDto>
     */
    @RequestMapping(value = "/enroll", method = RequestMethod.POST)
    public ResponseEntity<StudentCourseResponseDto> enroll(@Valid @RequestBody StudentCourseRequestDto studentCourseRequestDto) {
        studentCourseRequestDto.setId(null);
        StudentCourse studentCourse = getStudentCourse(studentCourseRequestDto);
        final StudentCourseResponseDto studentCourseResponseDto = getStudentCourseDto(studentCourseService.enrollInCourse(studentCourse.getCourse().getId(), studentCourse.getStudent().getId()));
        return new ResponseEntity<>(studentCourseResponseDto, HttpStatus.OK);
    }

    /**
     * Finds unaccepted applications
     *
     * @return ResponseEntity<List<StudentCourseResponseDto>>
     */
    @RequestMapping(value = "/falseApplications", method = RequestMethod.GET)
    public ResponseEntity<List<StudentCourseResponseDto>> getUnacceptedStudentCourses(@RequestParam Long languageId) {
        final List<StudentCourse> studentCourses = studentCourseService.findUnacceptedApplications(languageId);
        final List<StudentCourseResponseDto> studentCourseDtoList = new ArrayList<>();
        studentCourses.stream()
                .forEach((StudentCourse) -> studentCourseDtoList.add(getStudentCourseDto(StudentCourse)));
        return new ResponseEntity<>(studentCourseDtoList, HttpStatus.OK);
    }

    /**
     * Accepts application
     *
     * @param id - id
     * @return ResponseEntity<StudentCourseResponseDto>
     */
    @RequestMapping(value = "accept/{id}", method = RequestMethod.PUT)
    public ResponseEntity<StudentCourseResponseDto> accept(@PathVariable Long id) {
        final StudentCourseResponseDto studentCourseResponseDto = getStudentCourseDto(studentCourseService.acceptApplication(id));
        return new ResponseEntity<>(studentCourseResponseDto, HttpStatus.OK);

    }

    private StudentCourse getStudentCourse(StudentCourseRequestDto studentCourseRequestDto) {
        final StudentCourse studentCourse = mapper.map(studentCourseRequestDto, StudentCourse.class);
        final Course course = new Course();
        final Student student = new Student();
        course.setId(studentCourseRequestDto.getCourseid());
        studentCourse.setCourse(course);
        student.setId(studentCourseRequestDto.getStudentid());
        studentCourse.setStudent(student);

        return studentCourse;
    }

    private StudentCourseResponseDto getStudentCourseDto(StudentCourse studentCourse) {
        final StudentCourseResponseDto studentResponseDto = mapper.map(studentCourse, StudentCourseResponseDto.class);
        studentResponseDto.setCourseid(studentCourse.getCourse().getId());
        studentResponseDto.setStudentid(studentCourse.getStudent().getId());
        return studentResponseDto;
    }
}
