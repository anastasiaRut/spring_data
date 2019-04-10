package com.it.app.service;

import com.it.app.model.Language;

import java.util.List;

public interface LanguageService {
    Language addLanguage(Language language);

    void deleteById(Long id);

    void delete(Language entity);

    Language getById(Long id);

    Language editLanguage(Language language);

    List<Language> getAll();

    Language getLanguageWithTutors(String language);

    List<Language> getAllWithTutors();

}
