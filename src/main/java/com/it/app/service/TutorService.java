package com.it.app.service;

import com.it.app.model.Tutor;

import java.util.List;

public interface TutorService {
    Tutor addTutor(Tutor tutor);

    void deleteById(Long id);

    void delete(Tutor entity);

    Tutor getOne(Long id);

    Tutor editTutor(Tutor tutor);

    List<Tutor> findAll();

    List<Tutor> findTutorsByLanguage(String language);
}
