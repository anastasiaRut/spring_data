package com.it.app.service.impl;

import com.it.app.component.LocalizedMessageSource;
import com.it.app.model.Language;
import com.it.app.repository.LanguageRepository;
import com.it.app.service.LanguageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * The class represents a Implementation of LanguageService interface
 *
 * @author A. Rutkouskaya
 */
@Service
@Transactional
public class LanguageServiceImpl implements LanguageService {
    private final LocalizedMessageSource localizedMessageSource;

    private final LanguageRepository languageRepository;

    public LanguageServiceImpl(LocalizedMessageSource localizedMessageSource, LanguageRepository languageRepository) {
        this.localizedMessageSource = localizedMessageSource;
        this.languageRepository = languageRepository;
    }

    @Override
    public Language save(Language language) {
        validate(language.getId() != null, localizedMessageSource.getMessage("error.language.notHaveId", new Object[]{}));
        validate(languageRepository.existsByName(language.getName()), localizedMessageSource.getMessage("error.language.name.notUnique", new Object[]{}));
        return languageRepository.saveAndFlush(language);
    }

    @Override
    public Language findById(Long id) {
        return languageRepository.findById(id).orElseThrow(() -> new RuntimeException(localizedMessageSource.getMessage("error.language.notExist", new Object[]{})));
    }

    @Override
    public void deleteById(Long id) {
        findById(id);
        languageRepository.deleteById(id);
    }

    @Override
    public void delete(Language entity) {
        final Long id = entity.getId();
        validate(id == null, localizedMessageSource.getMessage("error.language.haveId", new Object[]{}));
        findById(id);
        languageRepository.delete(entity);
    }

    @Override
    public Language getOne(Long id) {
        return languageRepository.getOne(id);
    }

    @Override
    public Language update(Language language) {
        validate(language.getId() == null, localizedMessageSource.getMessage("error.language.haveId", new Object[]{}));
        findById(language.getId());
        validate(languageRepository.existsByName(language.getName()), localizedMessageSource.getMessage("error.language.name.notUnique", new Object[]{}));
        return languageRepository.saveAndFlush(language);
    }

    @Override
    public List<Language> findAll() {
        return languageRepository.findAll();
    }

    @Override
    public Language findLanguageWithTutors(String language) {
        return languageRepository.findLanguageWithTutors(language);
    }

    @Override
    public List<Language> findAllWithTutors() {
        return languageRepository.findAllWithTutors();
    }

    private void validate(boolean expression, String errorMessage) {
        if (expression) {
            throw new RuntimeException(errorMessage);
        }
    }
}