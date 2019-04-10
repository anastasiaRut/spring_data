package com.it.app.service.impl;

import com.it.app.model.Language;
import com.it.app.repository.LanguageRepository;
import com.it.app.service.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LanguageServiceImpl implements LanguageService {
    @Autowired
    LanguageRepository languageRepository;

    @Override
    public Language addLanguage(Language language) {
        Language savedLanguage = languageRepository.saveAndFlush(language);
        return savedLanguage;
    }

    @Override
    public void deleteById(Long id) {
        languageRepository.deleteById(id);
    }

    @Override
    public void delete(Language entity) {
        languageRepository.delete(entity);
    }

    @Override
    public Language getById(Long id) {
        return languageRepository.getOne(id);
    }

    @Override
    public Language editLanguage(Language language) {
        return languageRepository.saveAndFlush(language);
    }

    @Override
    public List<Language> getAll() {
        return languageRepository.findAll();
    }

    @Override
    public Language getLanguageWithTutors(String language) {
        return languageRepository.findLanguageWithTutors(language);
    }

    @Override
    public List<Language> getAllWithTutors() {
        return languageRepository.findAllWithTutors();
    }
}