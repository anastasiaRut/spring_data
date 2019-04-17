package com.it.app.service.impl;

import com.it.app.model.Course;
import com.it.app.repository.CourseRepository;
import com.it.app.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CourseServiceImpl implements CourseService {
    @Autowired
    CourseRepository courseRepository;

    @Override
    public Course addCourse(Course course) {
        Course savedCourse = courseRepository.saveAndFlush(course);
        return savedCourse;
    }

    @Override
    public void deleteById(Long id) {
        courseRepository.deleteById(id);
    }

    @Override
    public void delete(Course entity) {
        courseRepository.delete(entity);
    }

    @Override
    public Course getOne(Long id) {
        return courseRepository.getOne(id);
    }

    @Override
    public Course editCourse(Course course) {
        return courseRepository.saveAndFlush(course);
    }

    @Override
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    @Override
    public List<Course> findCoursesByLanguage(String language) {
        return courseRepository.findCoursesByLanguage(language);
    }

    @Override
    public List<Course> sortCoursesByFreePlaces() {
        return courseRepository.sortCoursesByFreePlaces();
    }
}
