package com.it.app.service;

import com.it.app.model.ScheduleMaker;
import com.it.app.model.StudentCourse;

import java.util.List;

public interface ScheduleMakerService {
    ScheduleMaker save(ScheduleMaker scheduleMaker);

    void deleteById(Long id);

    void delete(ScheduleMaker entity);

    ScheduleMaker getOne(Long id);

    ScheduleMaker update(ScheduleMaker scheduleMaker);

    List<ScheduleMaker> findAll();

    ScheduleMaker findById(Long id);

    List<ScheduleMaker> findAllByLanguage(String language);

}
