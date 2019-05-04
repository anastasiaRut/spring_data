package com.it.app.repository;

import com.it.app.model.TypeOfCourse;
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

    /**
     * Find out by name if this type exists
     *
     * @param typeName - name of type
     * @return boolean
     */
    boolean existsByName(String typeName);
}