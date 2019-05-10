package com.it.app.service.impl;

import com.it.app.component.LocalizedMessageSource;
import com.it.app.model.Course;
import com.it.app.model.Level;
import com.it.app.repository.CourseRepository;
import com.it.app.service.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

/**
 * The class represents a Implementation of CourseService interface
 *
 * @author A. Rutkouskaya
 */
@Service
@Transactional
public class CourseServiceImpl implements CourseService {
    private final LocalizedMessageSource localizedMessageSource;

    private final CourseRepository courseRepository;

    private final LanguageService languageService;

    private final LevelService levelService;

    private final TutorService tutorService;

    private final TypeOfCourseService typeOfCourseService;

    public CourseServiceImpl(LocalizedMessageSource localizedMessageSource, CourseRepository courseRepository, LanguageService languageService, LevelService levelService, TutorService tutorService, TypeOfCourseService typeOfCourseService) {
        this.localizedMessageSource = localizedMessageSource;
        this.courseRepository = courseRepository;
        this.languageService = languageService;
        this.levelService = levelService;
        this.tutorService = tutorService;
        this.typeOfCourseService = typeOfCourseService;
    }

    @Override
    public Course save(Course course) {
        validate(course.getId() != null, localizedMessageSource.getMessage("error.course.notHaveId", new Object[]{}));
        return saveAndFlush(course);
    }

    @Override
    public void deleteById(Long id) {
        findById(id);
        courseRepository.deleteById(id);
    }

    @Override
    public void delete(Course entity) {
        final Long id = entity.getId();
        validate(id == null, localizedMessageSource.getMessage("error.course.haveId", new Object[]{}));
        findById(id);
        courseRepository.delete(entity);
    }

    @Override
    public Course getOne(Long id) {
        return courseRepository.getOne(id);
    }

    @Override
    public Course update(Course course) {
        validate(course.getId() == null, localizedMessageSource.getMessage("error.course.haveId", new Object[]{}));
        findById(course.getId());
        return saveAndFlush(course);
    }

    @Override
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    @Override
    public Course findById(Long id) {
        return courseRepository.findById(id).orElseThrow(() -> new RuntimeException(localizedMessageSource.getMessage("error.course.notExist", new Object[]{})));
    }

    @Override
    public List<Course> findCoursesByLanguage(String language) {
        return courseRepository.findCoursesByLanguage(language);
    }

    @Override
    public List<Course> sortCoursesByFreePlaces() {
        return courseRepository.sortCoursesByFreePlaces();
    }

    private void validate(boolean expression, String errorMessage) {
        if (expression) {
            throw new RuntimeException(errorMessage);
        }
    }

    private Course saveAndFlush(Course course) {
        validate(course.getLanguage() == null || course.getLanguage().getId() == null, localizedMessageSource.getMessage("error.course.language.isNull", new Object[]{}));
        course.setLanguage(languageService.findById(course.getLanguage().getId()));
        validate(course.getLevel() == null || course.getLevel().getId() == null, localizedMessageSource.getMessage("error.course.level.isNull", new Object[]{}));
        course.setLevel(levelService.findById(course.getLevel().getId()));
        validate(course.getTutor() == null || course.getTutor().getId() == null, localizedMessageSource.getMessage("error.course.tutor.isNull", new Object[]{}));
        course.setTutor(tutorService.findById(course.getTutor().getId()));
        Set<Level> levels = course.getTutor().getLevels();
        boolean levelOfTutor = false;
        for (Level level : levels) {
            if (level.getId() == course.getLevel().getId()) {
                levelOfTutor = true;
                break;
            }
        }
        validate(!levelOfTutor, localizedMessageSource.getMessage("error.course.level.notOfThisTutor", new Object[]{}));
        validate(course.getTutor().getLanguage().getId() != course.getLanguage().getId(), localizedMessageSource.getMessage("error.course.language.notOfThisTutor", new Object[]{}));
        validate(course.getTypeOfCourse() == null || course.getTypeOfCourse().getId() == null, localizedMessageSource.getMessage("error.course.typeOfCourse.isNull", new Object[]{}));
        course.setTypeOfCourse(typeOfCourseService.findById(course.getTypeOfCourse().getId()));

        return courseRepository.saveAndFlush(course);
    }
}
