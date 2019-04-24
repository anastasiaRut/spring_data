package com.it.app.service.impl;

import com.it.app.model.TypeOfCourse;
import com.it.app.repository.TypeOfCourseRepository;
import com.it.app.service.TypeOfCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TypeOfCourseServiceImpl implements TypeOfCourseService {

    @Autowired
    TypeOfCourseRepository typeOfCourseRepository;

    @Override
    public TypeOfCourse addTypeOfCourse(TypeOfCourse typeOfCourse) {
        TypeOfCourse savedTypeOfCourse = typeOfCourseRepository.saveAndFlush(typeOfCourse);
        return savedTypeOfCourse;
    }

    @Override
    public void deleteById(Long id) {
        typeOfCourseRepository.deleteById(id);
    }

    @Override
    public void delete(TypeOfCourse entity) {
        typeOfCourseRepository.delete(entity);
    }

    @Override
    public TypeOfCourse getOne(Long id) {
        return typeOfCourseRepository.getOne(id);
    }

    @Override
    public TypeOfCourse editTypeOfCourse(TypeOfCourse typeOfCourse) {
        return typeOfCourseRepository.saveAndFlush(typeOfCourse);
    }

    @Override
    public List<TypeOfCourse> findAll() {
        return typeOfCourseRepository.findAll();
    }

    @Override
    public TypeOfCourse findByName(String name) {
        return typeOfCourseRepository.findByName(name);
    }
}
