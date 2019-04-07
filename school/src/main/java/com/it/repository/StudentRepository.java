package com.it.repository;

import com.it.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface for operations on a repository for Student entity
 *
 * @author A. Rutkouskaya
 * @see Student
 */
public interface StudentRepository extends JpaRepository<Student, Long> {

    /**
     * Find Student by phone number
     * JPA QL implementation
     *
     * @param phoneNumber - phone number
     * @return Student
     */
    Student findByPhoneNumber(String phoneNumber);
}
