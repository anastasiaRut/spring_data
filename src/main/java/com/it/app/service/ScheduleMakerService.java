package com.it.app.service;

import com.it.app.model.ScheduleMaker;

import java.util.List;

public interface ScheduleMakerService {
    ScheduleMaker addScheduleMaker(ScheduleMaker scheduleMaker);

    void deleteById(Long id);

    void delete(ScheduleMaker entity);

    ScheduleMaker getOne(Long id);

    ScheduleMaker editScheduleMaker(ScheduleMaker scheduleMaker);

    List<ScheduleMaker> findAll();

    List<ScheduleMaker> findAllByLanguage(String language);

}
