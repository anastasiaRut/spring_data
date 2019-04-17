package com.it.app.service;

import com.it.app.model.StudentCourse;

import java.util.List;

public interface StudentCourseService {
    StudentCourse addStudentCourse(StudentCourse studentCourse);

    void deleteById(Long id);

    void delete(StudentCourse entity);

    StudentCourse getOne(Long id);

    StudentCourse editStudentCourse(StudentCourse studentCourse);

    List<StudentCourse> findAll();

    List<StudentCourse> findUnacceptedApplications();
}
