package com.it.app.service;

import com.it.app.model.Course;
import com.it.app.repository.CourseRepository;

import java.util.List;

/**
 * Service for Course entity
 *
 * @author A. Rutkouskaya
 * @see Course
 * @see CourseRepository
 */
public interface CourseService {
    /**
     * Save Course entity in database
     *
     * @param course - course
     * @return
     */
    Course save(Course course);

    /**
     * Delete entity from database by id
     *
     * @param id - id
     */
    void deleteById(Long id);

    /**
     * Delete entity from database
     *
     * @param entity - Course entity
     */
    void delete(Course entity);

    /**
     * Returns a reference to the Course entity
     *
     * @param id - id
     * @return Course
     */
    Course getOne(Long id);

    /**
     * Updates Course entity
     *
     * @param course - Course
     * @return Course
     */
    Course update(Course course);

    /**
     * Finds Course by id
     *
     * @param id - id
     * @return Course
     */
    Course findById(Long id);

    /**
     * Finds all Courses
     *
     * @return List<Course>
     */
    List<Course> findAll();

    /**
     * Find Courses by Language
     * JPA QL implementation
     *
     * @return List<Course>
     */
    List<Course> findCoursesByLanguage(String language);

    /**
     * Sort Courses by free places
     * JPA QL implementation
     *
     * @return List<Course>
     */
    List<Course> sortCoursesByFreePlaces();
}
