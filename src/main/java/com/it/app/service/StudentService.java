package com.it.app.service;

import com.it.app.model.Student;
import com.it.app.model.Student;
import com.it.app.repository.StudentRepository;

import java.util.List;

/**
 * Service for Student entity
 *
 * @author A. Rutkouskaya
 * @see Student
 * @see StudentRepository
 */
public interface StudentService {
    /**
     * Save Student entity in database
     *
     * @param student - student
     * @return
     */
    Student save(Student student);

    /**
     * Delete entity from database by id
     *
     * @param id - id
     */
    void deleteById(Long id);

    /**
     * Delete entity from database
     *
     * @param entity - Student entity
     */
    void delete(Student entity);

    /**
     * Returns a reference to the Student entity
     *
     * @param id - id
     * @return Student
     */
    Student getOne(Long id);

    /**
     * Updates Student entity
     *
     * @param student - Student
     * @return Student
     */
    Student update(Student student);

    /**
     * Finds Student by id
     *
     * @param id - id
     * @return Student
     */
    Student findById(Long id);

    /**
     * Finds all Students
     *
     * @return List<Student>
     */
    List<Student> findAll();


    /**
     * Finds Student by phone number
     * JPA QL implementation
     *
     * @param phoneNumber - phone number
     * @return Student
     */
    Student findByPhoneNumber(String phoneNumber);
}
