package com.it.app.service;

import com.it.app.model.StudentCourse;

import java.util.List;

public interface StudentCourseService {
    StudentCourse save(StudentCourse studentCourse);

    void deleteById(Long id);

    void delete(StudentCourse entity);

    StudentCourse getOne(Long id);

    StudentCourse update(StudentCourse studentCourse);

    List<StudentCourse> findAll();

    StudentCourse findById(Long id);

    boolean isEnrolled(Long courseId, Long studentId);

    StudentCourse acceptApplication(Long studentCourseId);

    List<StudentCourse> findUnacceptedApplications();

    StudentCourse enrollInCourse(Long courseId, Long studentId);

}
