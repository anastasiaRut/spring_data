package com.it.app.unit.service;

import com.it.app.component.LocalizedMessageSource;
import com.it.app.model.Course;
import com.it.app.model.Student;
import com.it.app.model.StudentCourse;
import com.it.app.repository.StudentCourseRepository;
import com.it.app.service.CourseService;
import com.it.app.service.StudentService;
import com.it.app.service.impl.StudentCourseServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StudentCourseServiceImplTest {
    @InjectMocks
    private StudentCourseServiceImpl studentCourseService;

    @Mock
    private LocalizedMessageSource localizedMessageSource;

    @Mock
    private StudentCourseRepository studentCourseRepository;

    @Mock
    private StudentService studentService;

    @Mock
    private CourseService courseService;

    @Test
    public void testFindAll() {
        final List<StudentCourse> courseList = Collections.singletonList(new StudentCourse());
        when(studentCourseRepository.findAll()).thenReturn(courseList);
        assertEquals(studentCourseService.findAll(), courseList);
    }

    @Test
    public void testIsEnrolled() {
        final StudentCourse studentCourse = new StudentCourse();
        Course course = new Course();
        course.setId(1l);
        Student student = new Student();
        student.setId(1l);
        studentCourse.setCourse(course);
        studentCourse.setStudent(student);
        when(studentCourseRepository.findEnroll(any(Long.class), any(Long.class))).thenReturn(studentCourse);
        assertEquals(true, studentCourseService.isEnrolled(1l, 1l));
        assertEquals(false, studentCourseService.isEnrolled(null, null));
    }

    @Test
    public void acceptApplication() {
        final StudentCourse studentCourse = new StudentCourse();
        studentCourse.setStatus(false);
        studentCourse.setId(1l);
        final Student student = new Student();
        final Course course = new Course();
        student.setId(1l);
        course.setId(1l);
        studentCourse.setStudent(student);
        short places = 10;
        course.setPlaces(places);
        studentCourse.setCourse(course);
        when(studentService.findById(1L)).thenReturn(student);
        when(courseService.findById(1L)).thenReturn(course);
        when(studentCourseRepository.saveAndFlush(studentCourse)).thenReturn(studentCourse);
        when(studentCourseRepository.findById(1L)).thenReturn(Optional.of(studentCourse));
        assertEquals(studentCourseService.update(studentCourse), studentCourse);
        when(studentCourseService.acceptApplication(studentCourse.getId())).thenReturn(studentCourse);
        places=10;
        course.setPlaces(places);
        assertEquals(--places,studentCourseService.acceptApplication(studentCourse.getId()).getCourse().getPlaces());
    }

    @Test
    public void testFindById() {
        final StudentCourse studentCourse = new StudentCourse();
        when(studentCourseRepository.findById(any(Long.class))).thenReturn(Optional.of(studentCourse));
        assertEquals(studentCourseService.findById(1L), studentCourse);
    }

    @Test
    public void testSave() {
        final StudentCourse studentCourse = new StudentCourse();
        final Student student = new Student();
        final Course course = new Course();
        student.setId(1l);
        course.setId(1l);
        studentCourse.setStudent(student);
        studentCourse.setCourse(course);
        when(studentService.findById(1L)).thenReturn(student);
        when(courseService.findById(1L)).thenReturn(course);
        when(studentCourseRepository.saveAndFlush(studentCourse)).thenReturn(studentCourse);
        assertEquals(studentCourseService.save(studentCourse), studentCourse);
    }

    @Test
    public void testUpdate() {
        final StudentCourse studentCourse = new StudentCourse();
        studentCourse.setId(1l);
        final Student student = new Student();
        final Course course = new Course();
        student.setId(1l);
        course.setId(1l);
        studentCourse.setStudent(student);
        studentCourse.setCourse(course);
        when(studentService.findById(1L)).thenReturn(student);
        when(courseService.findById(1L)).thenReturn(course);
        when(studentCourseRepository.saveAndFlush(studentCourse)).thenReturn(studentCourse);
        when(studentCourseRepository.findById(1L)).thenReturn(Optional.of(studentCourse));
        assertEquals(studentCourseService.update(studentCourse), studentCourse);
    }

    @Test
    public void testDelete() {
        final StudentCourse studentCourse = new StudentCourse();
        studentCourse.setId(1L);
        doNothing().when(studentCourseRepository).delete(studentCourse);
        when(studentCourseRepository.findById(1L)).thenReturn(Optional.of(studentCourse));
        assertDoesNotThrow(() -> studentCourseService.delete(studentCourse));
    }

    @Test
    public void testDeleteById() {
        final StudentCourse studentCourse = new StudentCourse();
        studentCourse.setId(1L);
        when(studentCourseRepository.findById(1L)).thenReturn(Optional.of(studentCourse));
        doNothing().when(studentCourseRepository).deleteById(any(Long.class));
        assertDoesNotThrow(() -> studentCourseService.deleteById(1L));
    }

}