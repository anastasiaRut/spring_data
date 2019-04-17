package com.it.app.service;

import com.it.app.model.Student;

import java.util.List;

public interface StudentService {
    Student addStudent(Student student);

    void deleteById(Long id);

    void delete(Student entity);

    Student getOne(Long id);

    Student editStudent(Student student);

    List<Student> findAll();

    Student findByPhoneNumber(String phoneNumber);
}
