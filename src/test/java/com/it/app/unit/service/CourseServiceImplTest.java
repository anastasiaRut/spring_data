package com.it.app.unit.service;

import com.it.app.component.LocalizedMessageSource;
import com.it.app.model.*;
import com.it.app.repository.CourseRepository;
import com.it.app.service.LanguageService;
import com.it.app.service.LevelService;
import com.it.app.service.TutorService;
import com.it.app.service.TypeOfCourseService;
import com.it.app.service.impl.CourseServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CourseServiceImplTest {
    @InjectMocks
    private CourseServiceImpl courseService;

    @Mock
    private LocalizedMessageSource localizedMessageSource;

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private TutorService tutorService;

    @Mock
    private LanguageService languageService;

    @Mock
    private LevelService levelService;

    @Mock
    private TypeOfCourseService typeOfCourseService;

    @Test
    public void testFindAll() {
        final List<Course> courseList = Collections.singletonList(new Course());
        when(courseRepository.findAll()).thenReturn(courseList);
        assertEquals(courseService.findAll(), courseList);
    }

    @Test
    public void testFindById() {
        final Course course = new Course();
        when(courseRepository.findById(any(Long.class))).thenReturn(Optional.of(course));
        assertEquals(courseService.findById(1L), course);
    }

    @Test
    public void testSave() {
        final Course course = new Course();
        final Language language = new Language();
        final Level level = new Level();
        final Tutor tutor = new Tutor();
        final TypeOfCourse typeOfCourse = new TypeOfCourse();
        level.setId(1l);
        level.setName("level");
        language.setId(1l);
        language.setName("language");
        typeOfCourse.setId(1l);
        typeOfCourse.setName("type");
        tutor.setId(1l);
        tutor.setLanguage(language);
        tutor.setSurname("surname");
        tutor.setName("tutor");
        final Set<Level> levels = Collections.singleton(level);
        tutor.setLevels(levels);
        course.setLanguage(language);
        course.setTutor(tutor);
        course.setTypeOfCourse(typeOfCourse);
        course.setLevel(level);
        when(languageService.findById(1L)).thenReturn(language);
        when(tutorService.findById(1L)).thenReturn(tutor);
        when(tutorService.findById(1L)).thenReturn(tutor);
        when(typeOfCourseService.findById(1L)).thenReturn(typeOfCourse);
        when(levelService.findById(1L)).thenReturn(level);
        when(courseRepository.saveAndFlush(course)).thenReturn(course);
        assertEquals(courseService.save(course), course);
    }

    @Test
    public void testUpdate() {
        final Course course = new Course();
        course.setId(1l);
        final Language language = new Language();
        final Level level = new Level();
        final Tutor tutor = new Tutor();
        final TypeOfCourse typeOfCourse = new TypeOfCourse();
        level.setId(1l);
        level.setName("level");
        language.setId(1l);
        language.setName("language");
        typeOfCourse.setId(1l);
        typeOfCourse.setName("type");
        tutor.setId(1l);
        tutor.setLanguage(language);
        tutor.setSurname("surname");
        tutor.setName("tutor");
        final Set<Level> levels = Collections.singleton(level);
        tutor.setLevels(levels);
        course.setLanguage(language);
        course.setTutor(tutor);
        course.setTypeOfCourse(typeOfCourse);
        course.setLevel(level);
        when(languageService.findById(1L)).thenReturn(language);
        when(tutorService.findById(1L)).thenReturn(tutor);
        when(tutorService.findById(1L)).thenReturn(tutor);
        when(typeOfCourseService.findById(1L)).thenReturn(typeOfCourse);
        when(levelService.findById(1L)).thenReturn(level);
        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));
        when(courseRepository.saveAndFlush(course)).thenReturn(course);
        assertEquals(courseService.update(course), course);
    }

    @Test
    public void testDelete() {
        final Course course = new Course();
        course.setId(1L);
        doNothing().when(courseRepository).delete(course);
        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));
        assertDoesNotThrow(() -> courseService.delete(course));
    }

    @Test
    public void testDeleteById() {
        final Course course = new Course();
        course.setId(1L);
        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));
        doNothing().when(courseRepository).deleteById(any(Long.class));
        assertDoesNotThrow(() -> courseService.deleteById(1L));
    }
}