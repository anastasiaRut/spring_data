package com.it.app.service;

import com.it.app.model.Student;
import com.it.app.model.StudentCourse;

import java.util.List;

public interface StudentService {
    Student save(Student student);

    void deleteById(Long id);

    void delete(Student entity);

    Student getOne(Long id);

    Student update(Student student);

    Student findById(Long id);

    List<Student> findAll();

    Student findByPhoneNumber(String phoneNumber);
}
