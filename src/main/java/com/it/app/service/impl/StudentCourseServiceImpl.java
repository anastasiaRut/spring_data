package com.it.app.service.impl;

import com.it.app.component.LocalizedMessageSource;
import com.it.app.model.Course;
import com.it.app.model.Student;
import com.it.app.model.StudentCourse;
import com.it.app.repository.StudentCourseRepository;
import com.it.app.service.CourseService;
import com.it.app.service.StudentCourseService;
import com.it.app.service.StudentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * The class represents a Implementation of StudentCourseService interface
 *
 * @author A. Rutkouskaya
 */
@Service
@Transactional
public class StudentCourseServiceImpl implements StudentCourseService {
    private final StudentCourseRepository studentCourseRepository;

    private final LocalizedMessageSource localizedMessageSource;

    private final CourseService courseService;

    private final StudentService studentService;


    public StudentCourseServiceImpl(StudentCourseRepository studentCourseRepository, LocalizedMessageSource localizedMessageSource, CourseService courseService, StudentService studentService) {
        this.studentCourseRepository = studentCourseRepository;
        this.localizedMessageSource = localizedMessageSource;
        this.courseService = courseService;
        this.studentService = studentService;
    }

    @Override
    public StudentCourse save(StudentCourse studentCourse) {
        validate(studentCourse.getId() != null, localizedMessageSource.getMessage("error.studentCourse.notHaveId", new Object[]{}));
        return saveAndFlush(studentCourse);
    }

    @Override
    public void deleteById(Long id) {
        findById(id);
        studentCourseRepository.deleteById(id);
    }

    @Override
    public void delete(StudentCourse entity) {
        final Long id = entity.getId();
        validate(id == null, localizedMessageSource.getMessage("error.studentCourse.haveId", new Object[]{}));
        findById(id);
        studentCourseRepository.delete(entity);
    }

    @Override
    public StudentCourse getOne(Long id) {
        return studentCourseRepository.getOne(id);
    }

    @Override
    public StudentCourse update(StudentCourse studentCourse) {
        validate(studentCourse.getId() == null, localizedMessageSource.getMessage("error.studentCourse.haveId", new Object[]{}));
        findById(studentCourse.getId());
        return saveAndFlush(studentCourse);
    }

    @Override
    public List<StudentCourse> findAll() {
        return studentCourseRepository.findAll();
    }

    @Override
    public StudentCourse findById(Long id) {
        return studentCourseRepository.findById(id).orElseThrow(() -> new RuntimeException(localizedMessageSource.getMessage("error.studentCourse.notExist", new Object[]{})));
    }

    @Override
    public boolean isEnrolled(Long courseId, Long studentId) {
        return studentCourseRepository.findEnroll(courseId, studentId)!= null;
    }

    @Override
    public StudentCourse acceptApplication(Long studentCourseId) {
        StudentCourse studentCourse = findById(studentCourseId);
        studentCourse.setStatus(true);
        Course course = courseService.findById(studentCourse.getCourse().getId());
        short places = course.getPlaces();
        course.setPlaces(--places);
        courseService.update(course);
        return update(studentCourse);
    }

    @Override
    public List<StudentCourse> findUnacceptedApplications(Long languageId) {
        return studentCourseRepository.findUnacceptedApplications(languageId);
    }

    @Override
    public StudentCourse enrollInCourse(Long courseId, Long studentId) {
        Student student = studentService.findById(studentId);
        Course course = courseService.findById(courseId);
        validate(isEnrolled(courseId, studentId), localizedMessageSource.getMessage("error.student.isEnrolled", new Object[]{}));
        validate(course.getPlaces() == 0, localizedMessageSource.getMessage("error.course.isFull", new Object[]{}));
        StudentCourse studentCourse = new StudentCourse();
        studentCourse.setStatus(false);
        studentCourse.setStudent(student);
        studentCourse.setCourse(course);
        return save(studentCourse);
    }

    private void validate(boolean expression, String errorMessage) {
        if (expression) {
            throw new RuntimeException(errorMessage);
        }
    }

    private StudentCourse saveAndFlush(StudentCourse studentCourse) {
        validate(studentCourse.getCourse() == null || studentCourse.getCourse().getId() == null, localizedMessageSource.getMessage("error.studentCourse.course.isNull", new Object[]{}));
        studentCourse.setCourse(courseService.findById(studentCourse.getCourse().getId()));

        validate(studentCourse.getStudent() == null || studentCourse.getStudent().getId() == null, localizedMessageSource.getMessage("error.studentCourse.student.isNull", new Object[]{}));
        studentCourse.setStudent(studentService.findById(studentCourse.getStudent().getId()));

        return studentCourseRepository.saveAndFlush(studentCourse);
    }
}
