package com.it.app.service;

import com.it.app.model.Course;

import java.util.List;

public interface CourseService {
    Course addCourse(Course course);

    void deleteById(Long id);

    void delete(Course entity);

    Course getOne(Long id);

    Course editCourse(Course course);

    List<Course> findAll();

    List<Course> findCoursesByLanguage(String language);

    List<Course> sortCoursesByFreePlaces();
}
