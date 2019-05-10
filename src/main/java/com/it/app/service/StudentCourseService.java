package com.it.app.service;

import com.it.app.model.StudentCourse;
import com.it.app.repository.StudentCourseRepository;

import java.util.List;

/**
 * Service for StudentCourse entity
 *
 * @author A. Rutkouskaya
 * @see StudentCourse
 * @see StudentCourseRepository
 */
public interface StudentCourseService {
    /**
     * Save StudentCourse entity in database
     *
     * @param studentCourse - studentCourse
     * @return
     */
    StudentCourse save(StudentCourse studentCourse);

    /**
     * Delete entity from database by id
     *
     * @param id - id
     */
    void deleteById(Long id);

    /**
     * Delete entity from database
     *
     * @param entity - StudentCourse entity
     */
    void delete(StudentCourse entity);

    /**
     * Returns a reference to the StudentCourse entity
     *
     * @param id - id
     * @return StudentCourse
     */
    StudentCourse getOne(Long id);

    /**
     * Updates StudentCourse entity
     *
     * @param studentCourse - StudentCourse
     * @return StudentCourse
     */
    StudentCourse update(StudentCourse studentCourse);

    /**
     * Finds StudentCourse by id
     *
     * @param id - id
     * @return StudentCourse
     */
    StudentCourse findById(Long id);

    /**
     * Finds all StudentCourses
     *
     * @return List<StudentCourse>
     */
    List<StudentCourse> findAll();

    /**
     * Find out if a Student is enrolled in a Course
     *
     * @param courseId  - id of course
     * @param studentId - id of student
     * @return boolean
     */
    boolean isEnrolled(Long courseId, Long studentId);

    /**
     * Accepts application by id (sets status true)
     *
     * @param studentCourseId - id of studentCourse
     * @return StudentCourse
     */
    StudentCourse acceptApplication(Long studentCourseId);

    /**
     * Finds unaccepted applications where status is false
     *
     * @param languageId - id of language
     * @return List<StudentCourse>
     */
    List<StudentCourse> findUnacceptedApplications(Long languageId);

    /**
     * Enrolls a Student in a Course
     *
     * @param courseId  - id of course
     * @param studentId - id of student
     * @return StudentCourse
     */
    StudentCourse enrollInCourse(Long courseId, Long studentId);

}
