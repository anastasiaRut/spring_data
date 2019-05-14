package com.it.app.repository;

import com.it.app.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Interface for operations on a repository for Course entity
 *
 * @author A. Rutkouskaya
 * @see Course
 */
public interface CourseRepository extends JpaRepository<Course, Long> {

    /**
     * Find Courses by Language
     * JPA QL implementation
     *
     * @return List<Course>
     */
    @Query("FROM Course course WHERE course.language.name = :name")
    List<Course> findCoursesByLanguage(@Param("name") String language);

    /**
     * Sort Courses by free places
     * JPA QL implementation
     *
     * @return List<Course>
     */
    @Query("FROM Course course order by course.places desc")
    List<Course> sortCoursesByFreePlaces();

}
