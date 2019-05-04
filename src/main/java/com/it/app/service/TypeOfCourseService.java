package com.it.app.service;

import com.it.app.model.TypeOfCourse;

import java.util.List;

public interface TypeOfCourseService {
    TypeOfCourse save(TypeOfCourse typeOfCourse);

    void deleteById(Long id);

    void delete(TypeOfCourse entity);

    TypeOfCourse getOne(Long id);

    TypeOfCourse findById(Long id);

    TypeOfCourse update(TypeOfCourse typeOfCourse);

    List<TypeOfCourse> findAll();

    TypeOfCourse findByName(String name);
}
