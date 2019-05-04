package com.it.app.service;

import com.it.app.model.Course;

import java.util.List;

public interface CourseService {
    Course save(Course course);

    void deleteById(Long id);

    void delete(Course entity);

    Course getOne(Long id);

    Course update(Course course);

    List<Course> findAll();

    Course findById(Long id);

    List<Course> findCoursesByLanguage(String language);

    List<Course> sortCoursesByFreePlaces();
}
