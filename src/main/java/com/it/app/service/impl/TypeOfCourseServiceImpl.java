package com.it.app.service.impl;

import com.it.app.component.LocalizedMessageSource;
import com.it.app.model.TypeOfCourse;
import com.it.app.repository.TypeOfCourseRepository;
import com.it.app.service.TypeOfCourseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TypeOfCourseServiceImpl implements TypeOfCourseService {

    private final LocalizedMessageSource localizedMessageSource;

    private final TypeOfCourseRepository typeOfCourseRepository;

    public TypeOfCourseServiceImpl(LocalizedMessageSource localizedMessageSource, TypeOfCourseRepository typeOfCourseRepository) {
        this.localizedMessageSource = localizedMessageSource;
        this.typeOfCourseRepository = typeOfCourseRepository;
    }

    @Override
    public TypeOfCourse save(TypeOfCourse typeOfCourse) {
        validate(typeOfCourse.getId() != null, localizedMessageSource.getMessage("error.typeOfCourse.notHaveId", new Object[]{}));
        validate(typeOfCourseRepository.existsByName(typeOfCourse.getName()), localizedMessageSource.getMessage("error.typeOfCourse.name.notUnique", new Object[]{}));
        return typeOfCourseRepository.saveAndFlush(typeOfCourse);
    }

    @Override
    public void deleteById(Long id) {
        findById(id);
        typeOfCourseRepository.deleteById(id);
    }

    @Override
    public void delete(TypeOfCourse entity) {
        final Long id = entity.getId();
        validate(id == null, localizedMessageSource.getMessage("error.typeOfCourse.haveId", new Object[]{}));
        findById(id);
        typeOfCourseRepository.delete(entity);
    }

    @Override
    public TypeOfCourse getOne(Long id) {
        return typeOfCourseRepository.getOne(id);
    }

    @Override
    public TypeOfCourse findById(Long id) {
        return typeOfCourseRepository.findById(id).orElseThrow(() -> new RuntimeException(localizedMessageSource.getMessage("error.typeOfCourse.notExist", new Object[]{})));    }

    @Override
    public TypeOfCourse update(TypeOfCourse typeOfCourse) {
        validate(typeOfCourse.getId() == null, localizedMessageSource.getMessage("error.typeOfCourse.haveId", new Object[]{}));
        findById(typeOfCourse.getId());
        validate(typeOfCourseRepository.existsByName(typeOfCourse.getName()), localizedMessageSource.getMessage("error.typeOfCourse.name.notUnique", new Object[]{}));
        return typeOfCourseRepository.saveAndFlush(typeOfCourse);
    }

    @Override
    public List<TypeOfCourse> findAll() {
        return typeOfCourseRepository.findAll();
    }

    @Override
    public TypeOfCourse findByName(String name) {
        validate(!typeOfCourseRepository.existsByName(name), localizedMessageSource.getMessage("error.typeOfCourse.notExist", new Object[]{}));
        return  typeOfCourseRepository.findByName(name);
    }

    private void validate(boolean expression, String errorMessage) {
        if (expression) {
            throw new RuntimeException(errorMessage);
        }
    }
}
