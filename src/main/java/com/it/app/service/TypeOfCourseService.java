package com.it.app.service;

import com.it.app.model.TypeOfCourse;
import com.it.app.repository.TypeOfCourseRepository;

import java.util.List;

/**
 * Service for TypeOfCourse entity
 *
 * @author A. Rutkouskaya
 * @see TypeOfCourse
 * @see TypeOfCourseRepository
 */
public interface TypeOfCourseService {
    /**
     * Save TypeOfCourse entity in database
     *
     * @param typeOfCourse - typeOfCourse
     * @return
     */
    TypeOfCourse save(TypeOfCourse typeOfCourse);

    /**
     * Delete entity from database by id
     *
     * @param id - id
     */
    void deleteById(Long id);

    /**
     * Delete entity from database
     *
     * @param entity - TypeOfCourse entity
     */
    void delete(TypeOfCourse entity);

    /**
     * Returns a reference to the TypeOfCourse entity
     *
     * @param id - id
     * @return TypeOfCourse
     */
    TypeOfCourse getOne(Long id);

    /**
     * Updates TypeOfCourse entity
     *
     * @param typeOfCourse - TypeOfCourse
     * @return TypeOfCourse
     */
    TypeOfCourse update(TypeOfCourse typeOfCourse);

    /**
     * Finds TypeOfCourse by id
     *
     * @param id - id
     * @return TypeOfCourse
     */
    TypeOfCourse findById(Long id);

    /**
     * Finds all TypeOfCourses
     *
     * @return List<TypeOfCourse>
     */
    List<TypeOfCourse> findAll();

    /**
     * Find TypeOfCourse by name
     *
     * @param name - name of type
     * @return TypeOfCourse
     */
    TypeOfCourse findByName(String name);
}
