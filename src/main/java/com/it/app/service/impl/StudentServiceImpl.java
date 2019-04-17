package com.it.app.service.impl;

import com.it.app.model.Student;
import com.it.app.repository.StudentRepository;
import com.it.app.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentRepository studentRepository;

    @Override
    public Student addStudent(Student student) {
        Student savedStudent = studentRepository.saveAndFlush(student);
        return savedStudent;
    }

    @Override
    public void deleteById(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public void delete(Student entity) {
        studentRepository.delete(entity);
    }

    @Override
    public Student getOne(Long id) {
        return studentRepository.getOne(id);
    }

    @Override
    public Student editStudent(Student student) {
        return studentRepository.saveAndFlush(student);
    }

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public Student findByPhoneNumber(String phoneNumber) {
        return studentRepository.findByPhoneNumber(phoneNumber);
    }
}
