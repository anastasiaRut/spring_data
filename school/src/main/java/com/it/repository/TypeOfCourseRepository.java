package com.it.repository;

import com.it.model.TypeOfCourse;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface for operations on a repository for TypeOfCourse entity
 *
 * @author A. Rutkouskaya
 * @see TypeOfCourse
 */
public interface TypeOfCourseRepository extends JpaRepository<TypeOfCourse, Long> {

    /**
     * Find TypeOfCourse by name
     *
     * @param name - name of type
     * @return TypeOfCourse
     */
    TypeOfCourse findByName(String name);
}