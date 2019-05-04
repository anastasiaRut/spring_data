package com.it.app.repository;

import com.it.app.model.StudentCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Interface for operations on a repository for StudentCourse entity
 *
 * @author A. Rutkouskaya
 * @see StudentCourse
 */
public interface StudentCourseRepository extends JpaRepository<StudentCourse, Long> {

    /**
     * Find unaccepted applications for enroll in Courses
     * JPA QL implementation
     *
     * @return List<StudentCourse>
     */
    @Query("FROM StudentCourse studentCourse WHERE studentCourse.status = false")
    List<StudentCourse> findUnacceptedApplications();

    @Query("FROM StudentCourse studentCourse WHERE studentCourse.course.id = :courseId AND studentCourse.student.id = :studentId")
    StudentCourse findEnroll(@Param("courseId") Long courseId, @Param("studentId") Long studentId);

}
