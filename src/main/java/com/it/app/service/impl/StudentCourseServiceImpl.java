package com.it.app.service.impl;

import com.it.app.model.StudentCourse;
import com.it.app.repository.StudentCourseRepository;
import com.it.app.service.StudentCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StudentCourseServiceImpl implements StudentCourseService {
    @Autowired
    StudentCourseRepository studentCourseRepository;

    @Override
    public StudentCourse addStudentCourse(StudentCourse studentCourse) {
        StudentCourse savedStudentCourse = studentCourseRepository.saveAndFlush(studentCourse);
        return savedStudentCourse;
    }

    @Override
    public void deleteById(Long id) {
        studentCourseRepository.deleteById(id);
    }

    @Override
    public void delete(StudentCourse entity) {
        studentCourseRepository.delete(entity);
    }

    @Override
    public StudentCourse getOne(Long id) {
        return studentCourseRepository.getOne(id);
    }

    @Override
    public StudentCourse editStudentCourse(StudentCourse StudentCourse) {
        return studentCourseRepository.saveAndFlush(StudentCourse);
    }

    @Override
    public List<StudentCourse> findAll() {
        return studentCourseRepository.findAll();
    }

    @Override
    public List<StudentCourse> findUnacceptedApplications() {
        return studentCourseRepository.findUnacceptedApplications();
    }
}
