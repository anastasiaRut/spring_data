package com.it.app.service;

import com.it.app.model.Language;

import java.util.List;

public interface LanguageService {
    Language save(Language language);

    void deleteById(Long id);

    void delete(Language entity);

    Language getOne(Long id);

    Language findById(Long id);

    Language update(Language language);

    List<Language> findAll();

    Language findLanguageWithTutors(String language);

    List<Language> findAllWithTutors();

}
