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

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<StudentCourseResponseDto>> getAll() {
        final List<StudentCourse> studentCourses = studentCourseService.findAll();
        final List<StudentCourseResponseDto> studentCourseDtoList = new ArrayList<>();
        studentCourses.stream()
                .forEach((StudentCourse) -> studentCourseDtoList.add(getStudentDto(StudentCourse)));
        return new ResponseEntity<>(studentCourseDtoList, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<StudentCourseResponseDto> save(@Valid @RequestBody StudentCourseRequestDto studentCourseRequestDto) {
        studentCourseRequestDto.setId(null);
        final StudentCourseResponseDto studentCourseResponseDto = getStudentDto(studentCourseService.save(getStudentCourse(studentCourseRequestDto)));
        return new ResponseEntity<>(studentCourseResponseDto, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<StudentCourseResponseDto> update(@Valid @RequestBody StudentCourseRequestDto studentCourseRequestDto, @PathVariable Long id) {
        if (!Objects.equals(id, studentCourseRequestDto.getId())) {
            throw new RuntimeException(localizedMessageSource.getMessage("controller.studentCourse.unexpectedId", new Object[]{}));
        }
        final StudentCourseResponseDto studentCourseResponseDto = getStudentDto(studentCourseService.update(getStudentCourse(studentCourseRequestDto)));
        return new ResponseEntity<>(studentCourseResponseDto, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        studentCourseService.deleteById(id);
    }


    @RequestMapping(value = "/enroll", method = RequestMethod.POST)
    public ResponseEntity<StudentCourseResponseDto> enroll(@Valid @RequestBody StudentCourseRequestDto studentCourseRequestDto) {
        studentCourseRequestDto.setId(null);
        StudentCourse studentCourse = getStudentCourse(studentCourseRequestDto);
        final StudentCourseResponseDto studentCourseResponseDto = getStudentDto(studentCourseService.enrollInCourse(studentCourse.getCourse().getId(), studentCourse.getStudent().getId()));
        return new ResponseEntity<>(studentCourseResponseDto, HttpStatus.OK);
    }

    @RequestMapping(value = "/falseApplications", method = RequestMethod.GET)
    public ResponseEntity<List<StudentCourseResponseDto>> getUnacceptedStudentCourses() {
        final List<StudentCourse> studentCourses = studentCourseService.findUnacceptedApplications();
        final List<StudentCourseResponseDto> studentCourseDtoList = new ArrayList<>();
        studentCourses.stream()
                .forEach((StudentCourse) -> studentCourseDtoList.add(getStudentDto(StudentCourse)));
        return new ResponseEntity<>(studentCourseDtoList, HttpStatus.OK);
    }

    @RequestMapping(value = "accept/{id}", method = RequestMethod.PUT)
    public ResponseEntity<StudentCourseResponseDto> accept(@PathVariable Long id) {
        final StudentCourseResponseDto studentCourseResponseDto = getStudentDto(studentCourseService.acceptApplication(id));
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

    private StudentCourseResponseDto getStudentDto(StudentCourse studentCourse) {
        final StudentCourseResponseDto studentResponseDto = mapper.map(studentCourse, StudentCourseResponseDto.class);
        studentResponseDto.setCourseid(studentCourse.getCourse().getId());
        studentResponseDto.setStudentid(studentCourse.getStudent().getId());
        return studentResponseDto;
    }
}
